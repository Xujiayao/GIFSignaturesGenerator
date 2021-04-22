package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.Main;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author Xujiayao
 */
public class HitokotoAPI implements Runnable {

	@Override
	public void run() {
		Variables.hitokotoData = ParseJSON.parseHitokotoJSON(getHitokoto());

		if (Variables.hitokotoData != null) {
			Variables.displayHitokotoData = Variables.hitokotoData[0] + "\n出自 " + Variables.hitokotoData[1];

			if (Variables.hitokotoData[0].length() > 21) {
				Platform.runLater(() -> Main.loginUI.text2.setY(438));
			}

			Platform.runLater(() -> Main.loginUI.text2.setText(Variables.displayHitokotoData));
		}
	}

	private String getHitokoto() {
		String data = null;
		BufferedReader reader = null;

		try {
			URLConnection conn = new URL("https://v1.hitokoto.cn/").openConnection();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

			data = reader.readLine();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				Dialogs.showExceptionDialog(e);
			}
		}

		return data;
	}
}
