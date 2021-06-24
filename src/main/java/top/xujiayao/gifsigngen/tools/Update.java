package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.client.methods.HttpGet;
import top.xujiayao.gifsigngen.ui.Dialogs;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Xujiayao
 */
public class Update implements Runnable {

	private boolean isManualRequest = false;
	private String[] parsedData;

	private double progress;

	public TimerTask timerTask;

	public Thread updateThread;

	@Override
	public void run() {
		try {
			String data = downloadJSON();

			parsedData = ParseJSON.parseUpdateJSON(data);

			if (parsedData.length == 0) {
				throw new CustomException("检查更新失败");
			}

			Platform.runLater(() -> {
				if (ConfigManager.config.preferences.checkBetaUpdates) {
					checkBetaUpdates();
				} else {
					if (parsedData[0].equals(Variables.LATEST_RELEASE_VERSION)) {
						isLatest();
					} else {
						Dialogs.showUpdateDialog(parsedData[0], parsedData[2], Integer.parseInt(parsedData[3]), parsedData[4], parsedData[5]);
					}
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}
	}

	private void checkBetaUpdates() {
		if (Integer.parseInt(parsedData[1]) > Integer.parseInt(parsedData[7])) {
			if (parsedData[0].equals(Variables.VERSION)) {
				isLatest();
			} else {
				Dialogs.showUpdateDialog(parsedData[0], parsedData[2], Integer.parseInt(parsedData[3]), parsedData[4], parsedData[5]);
			}
		} else {
			if (parsedData[6].equals(Variables.VERSION)) {
				isLatest();
			} else {
				Dialogs.showUpdateDialog(parsedData[6], parsedData[8], Integer.parseInt(parsedData[9]), parsedData[10], parsedData[11]);
			}
		}
	}

	private void isLatest() {
		if (isManualRequest) {
			Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
		}
	}

	private String downloadJSON() {
		HttpGet request = new HttpGet(Variables.CHECK_UPDATE_LINK);
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		return Utils.getHttpResponse(null, request);
	}

	public byte[] downloadUpdate(String link, int size, String split) throws Exception {
		InputStream is = null;
		ByteArrayOutputStream os = null;

		progress = 0;

		try {
			os = new ByteArrayOutputStream();

			startTimer(size, BigDecimal.valueOf(size / 1024.0 / 1024.0).setScale(2, RoundingMode.HALF_UP));


			for (int i = 1; i <= Integer.parseInt(split); i++) {
				URLConnection conn = new URL(link + i).openConnection();

				is = conn.getInputStream();

				byte[] data = readInputStream(is, size);

				if (data.length == 0) {
					return data;
				}

				os.write(data);
			}

			Platform.runLater(() -> {
				Dialogs.bar.setProgress(1);
				Dialogs.text.setText("完成 (100%)");
			});

			return os.toByteArray();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			if (is != null) {
				is.close();
			}

			if (os != null) {
				os.close();
			}
		}

		return new byte[0];
	}

	private void startTimer(double length, BigDecimal formattedLength) {
		timerTask = new TimerTask() {
			@Override
			public void run() {
				Dialogs.text.setText("下载中 (" + BigDecimal.valueOf(length / 1024.0 / 1024.0 * (progress / 100.0)).setScale(2, RoundingMode.HALF_UP) + " MB / " + formattedLength + " MB)");

				if (Dialogs.bar.getProgress() == 1) {
					System.out.println("a");

					this.cancel();
				}
			}
		};

		new Timer().schedule(timerTask, 0, 500);
	}

	private byte[] readInputStream(InputStream is, int length) {
		try {
			byte[] buffer = new byte[1];
			int len;

			double temp1 = 0;

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while ((len = is.read(buffer)) != -1) {
				if (updateThread.isInterrupted()) {
					is.close();
					bos.close();

					return new byte[0];
				}

				bos.write(buffer, 0, len);

				progress = progress + (100.0 / length);

				double temp2 = Double.parseDouble(String.format("%.1f", progress));

				if (temp1 != temp2) {
					Platform.runLater(() -> Dialogs.bar.setProgress(progress / 100));
				}

				temp1 = temp2;
			}

			bos.close();
			return bos.toByteArray();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return new byte[0];
	}

	public void setManualRequest(boolean manualRequest) {
		isManualRequest = manualRequest;
	}
}
