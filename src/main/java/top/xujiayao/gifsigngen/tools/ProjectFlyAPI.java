package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import top.xujiayao.gifsigngen.ui.Dialogs;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Xujiayao
 */
public class ProjectFlyAPI {

	private ProjectFlyAPI() {
		throw new IllegalStateException("工具类");
	}

	public static String getProfile(int useLinkNumber) {
		String[] links = {"https://api.projectfly.co.uk/api/v3/community/user/" + Variables.projectFlyData.loginData[0] + "/profile",
			  "https://api.projectfly.co.uk/api/v3/bookings/logbook/" + Variables.projectFlyData.loginData[0] + "?page=0",
			  "https://api.projectfly.co.uk/api/v3/community/user/" + Variables.projectFlyData.loginData[0] + "/passport"};

		HttpGet request = new HttpGet(links[useLinkNumber]);
		request.setHeader("Authorization", "Bearer " + Variables.projectFlyData.loginData[6]);
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		try {
			Variables.response = Variables.httpClient.execute(request);

			if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = Variables.response.getEntity();

				return EntityUtils.toString(httpEntity);
			} else if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				Platform.runLater(() -> Dialogs.showErrorDialog("发生错误", "令牌已过期，请返回上一步重新登录。"));
			} else {
				Platform.runLater(() -> Dialogs.showExceptionDialog(new CustomException("服务器返回状态码："
					  + Variables.response.getStatusLine().getStatusCode())));
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return null;
	}

	public static String login(String username, String password) {
		HttpPost request = new HttpPost("https://api.projectfly.co.uk/api/v3/login");
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		StringEntity entity = new StringEntity("username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/x-www-form-urlencoded");
		request.setEntity(entity);

		try {
			Variables.response = Variables.httpClient.execute(request);

			if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = Variables.response.getEntity();

				return EntityUtils.toString(httpEntity);
			} else if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				Platform.runLater(() -> Dialogs.showErrorDialog("发生错误", "无效的用户名或密码。"));
			} else {
				Platform.runLater(() -> Dialogs.showExceptionDialog(new CustomException("服务器返回状态码："
					  + Variables.response.getStatusLine().getStatusCode())));
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return null;
	}
}
