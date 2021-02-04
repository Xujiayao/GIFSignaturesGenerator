package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.ui.Dialogs;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ProjectFlyAPI {

	public String login(String username, String password) throws Exception {
		String data = null;
		BufferedReader reader = null;

		try {
			URLConnection conn = new URL("https://api.projectfly.co.uk/api/v3/login").openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write("username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8));
			writer.flush();
			writer.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("HTTP response code: 401"))
					Dialogs.showErrorDialog("发生错误", "无效的用户名或密码。");
				else
					Dialogs.showExceptionDialog(e);
			});
		} finally {
			if (reader != null)
				reader.close();
		}

		return data;
	}
}
