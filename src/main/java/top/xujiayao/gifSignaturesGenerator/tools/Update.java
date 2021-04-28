package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author Xujiayao
 */
public class Update implements Runnable {

	public boolean isManualRequest = false;

	@Override
	public void run() {
		try {
			boolean isManualRequest = this.isManualRequest;

			String data = downloadJSON();
			String[] parsedData = ParseJSON.parseUpdateJSON(data);

			Platform.runLater(() -> {
				if (Variables.checkBetaUpdates) {
					if (Integer.parseInt(parsedData[1]) > Integer.parseInt(parsedData[5])) {
						if (parsedData[0].equals(Variables.version)) {
							if (isManualRequest) {
								Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
							}
						} else {
							Dialogs.showUpdateDialog(parsedData[0], parsedData[2], parsedData[3]);
						}
					} else {
						if (parsedData[4].equals(Variables.version)) {
							if (isManualRequest) {
								Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
							}
						} else {
							Dialogs.showUpdateDialog(parsedData[4], parsedData[6], parsedData[7]);
						}
					}
				} else {
					if (parsedData[0].equals(Variables.latestReleaseVersion)) {
						if (isManualRequest) {
							Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
						}
					} else {
						Dialogs.showUpdateDialog(parsedData[0], parsedData[2], parsedData[3]);
					}
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}
	}

	private String downloadJSON() throws Exception {
		String data = null;
		BufferedReader reader = null;

		try {
			URLConnection conn = new URL(Variables.checkUpdateLink).openConnection();
			conn.setUseCaches(false);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		return data;
	}

	public byte[] downloadUpdate(String link) throws Exception {
		byte[] data;
		InputStream is = null;

		try {
			URLConnection conn = new URL(link).openConnection();

			is = conn.getInputStream();

			data = readInputStream(is, conn.getContentLength());

			Platform.runLater(() -> {
				Dialogs.bar.setProgress(1);
				Dialogs.text.setText("完成 (100%)");
			});

			return data;
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			if (is != null) {
				is.close();
			}
		}

		return null;
	}

	private double progress = 0;

	private byte[] readInputStream(InputStream inputStream, int length) {
		try {
			byte[] buffer = new byte[1];
			int len;
			progress = 0;

			double temp1 = 0;

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);

				progress = progress + (100.0 / length);

				double temp2 = Double.parseDouble(String.format("%.1f", progress));

				if (temp1 != temp2) {
					Platform.runLater(() -> {
						Dialogs.bar.setProgress(progress / 100);
						Dialogs.text.setText("下载中 (" + temp2 + "%)");
					});
				}

				temp1 = temp2;
			}

			bos.close();
			return bos.toByteArray();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return null;
	}
}
