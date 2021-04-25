package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import java.io.BufferedReader;
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
					if (parsedData[0].equals(Variables.version) && isManualRequest) {
						Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
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
}
