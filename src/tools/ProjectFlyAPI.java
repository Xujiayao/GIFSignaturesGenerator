package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javafx.application.Platform;
import ui.Dialogs;
import ui.LoginUI;

/**
 * @author Xujiayao
 */
public class ProjectFlyAPI {
	
	public static String unicodeToString(String unicode) {
		StringBuffer buffer = new StringBuffer();
		int maxLoop = unicode.length();  
		for (int i = 0; i < maxLoop; i++) {  
			if (unicode.charAt(i) == '\\') {  
				if ((i < maxLoop - 5) && ((unicode.charAt(i + 1) == 'u') || (unicode.charAt(i + 1) == 'U'))) {
					try {  
						buffer.append((char) Integer.parseInt(  
								unicode.substring(i + 2, i + 6), 16));  
						i += 5;  
					} catch (NumberFormatException e) {  
						buffer.append(unicode.charAt(i));  
					}
				}
			} else {  
				buffer.append(unicode.charAt(i));  
			}  
		}  
		return buffer.toString();
	}
	
	public static String login(String username, String password) {
		try {
			URL url = new URL("https://api.projectfly.co.uk/api/v3/login");
			
			URLConnection conn = url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");
			
			conn.connect();
			
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			
			String postValue1 = "username=" + URLEncoder.encode(username, "utf-8");
			String postValue2 = "&password=" + URLEncoder.encode(password, "utf-8");
			
			String postValue = postValue1 + postValue2;
			
			writer.write(postValue);
			writer.flush();
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
			
			String data = reader.readLine();
			
			reader.close();
			
			return data;
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("HTTP response code: 401")) {
					if (Variables.language.equals("English")) {
						Dialogs.showErrorDialog("Invalid username or password.", true);
					} else {
						Dialogs.showErrorDialog("无效的用户名或密码。", false);
					}
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		}
		
		Platform.runLater(() -> {
			if (Variables.language.equals("English")) {
				LoginUI.button.setText("Login");
			} else {
				LoginUI.button.setText("登录");
			}
			
			LoginUI.button.setDisable(false);
		});
		
		return null;
	}
}
