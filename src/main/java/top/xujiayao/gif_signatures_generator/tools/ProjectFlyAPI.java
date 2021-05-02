package top.xujiayao.gif_signatures_generator.tools;

import javafx.application.Platform;
import top.xujiayao.gif_signatures_generator.Main;
import top.xujiayao.gif_signatures_generator.ui.Dialogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Xujiayao
 */
public class ProjectFlyAPI {

	private ProjectFlyAPI() {
		throw new IllegalStateException("工具类");
	}

	public static String getProfile(int useLinkNumber) throws IOException {
		String data = null;

		String[] links = {"https://api.projectfly.co.uk/api/v3/community/user/" + Main.getProjectFlyData().getLoginData()[0] + "/profile",
			  "https://api.projectfly.co.uk/api/v3/bookings/logbook/" + Main.getProjectFlyData().getLoginData()[0] + "?page=0",
			  "https://api.projectfly.co.uk/api/v3/community/user/" + Main.getProjectFlyData().getLoginData()[0] + "/passport"};

		URLConnection conn = new URL(links[useLinkNumber]).openConnection();
		conn.addRequestProperty("Authorization", "Bearer " + Main.getProjectFlyData().getLoginData()[6]);
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("HTTP response code: 401")) {
					Dialogs.showErrorDialog("发生错误", "令牌已过期，请返回上一步重新登录。");
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		}

		return data;
	}

	public static String login(String username, String password) throws IOException {
		String data = null;

		URLConnection conn = new URL("https://api.projectfly.co.uk/api/v3/login").openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
			writer.write("username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8));
			writer.flush();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("HTTP response code: 401")) {
					Dialogs.showErrorDialog("发生错误", "无效的用户名或密码。");
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		}

		return data;
	}
}
