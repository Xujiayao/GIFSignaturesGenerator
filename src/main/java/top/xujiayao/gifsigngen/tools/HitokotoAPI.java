package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import top.xujiayao.gifsigngen.Main;
import top.xujiayao.gifsigngen.ui.Dialogs;

/**
 * @author Xujiayao
 */
public class HitokotoAPI implements Runnable {

	@Override
	public void run() {
		try {
			Variables.hitokotoData = ParseJSON.parseHitokotoJSON(getHitokoto());
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		if (Variables.hitokotoData.length != 0) {
			Variables.displayHitokotoData = Variables.hitokotoData[0] + "\n出自 " + Variables.hitokotoData[1];

			if (Variables.hitokotoData[0].length() > 21) {
				Platform.runLater(() -> Main.loginUI.text2.setY(438));
			}
		} else {
			Variables.displayHitokotoData = "没有个性，如何签名？\n出自 Xujiayao";
		}

		Platform.runLater(() -> Main.loginUI.text2.setText(Variables.displayHitokotoData));
	}

	private String getHitokoto() {
		HttpGet request = new HttpGet("https://v1.hitokoto.cn/");

		try {
			Variables.response = Variables.httpClient.execute(request);

			if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = Variables.response.getEntity();

				return EntityUtils.toString(httpEntity, "utf-8");
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
