package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
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
			String data = downloadJSON(Main.variables.checkUpdateLink);

			String[] parsedData = Main.parseJSON.parseUpdateJSON(data);

			Platform.runLater(() -> {
				if (parsedData != null) {
					boolean response = Main.dialogs.showConfirmDialog("检查更新", "有新版本可用！", "GIF签名图生成工具 " + parsedData[0] + " 现在可用（您是 " + Main.variables.version + "）。您想要现在更新吗？\n\n更新说明：\n\n" + parsedData[1].replaceAll("\\\\n", "\n-> "));

					if (response) {
						Main.dialogs.showMessageDialog("检查更新", "我还没做");
					}
				} else {
					if (isManualRequest)
						Main.dialogs.showMessageDialog("检查更新", "您正在使用最新版本的GIF签名图生成工具。");
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> Main.dialogs.showExceptionDialog(e));
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
			Platform.runLater(() -> Main.dialogs.showExceptionDialog(e));
		} finally {
			if (reader != null)
				reader.close();
		}

		return data;
	}
}
