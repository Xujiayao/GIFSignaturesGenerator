package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import io.gitee.xujiayao147.gifSignaturesGenerator.ui.Dialogs;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Update implements Runnable {

	public boolean isManualRequest = false;

	@Override
	public void run() {
		try {
			boolean isManualRequest = this.isManualRequest;
			String data = downloadJSON(Variables.checkUpdateLink);

			String[] parsedData = Main.parseJSON.parseUpdateJSON(data);

			Platform.runLater(() -> {
				if (parsedData != null) {
					Dialogs.showUpdateDialog(parsedData);
				} else {
					if (isManualRequest)
						Dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}
	}

	private String downloadJSON(String link) throws Exception {
		String data = null;
		BufferedReader reader = null;

		try {
			URLConnection conn = new URL(link).openConnection();
			conn.setUseCaches(false);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			if (reader != null)
				reader.close();
		}

		return data;
	}
}
