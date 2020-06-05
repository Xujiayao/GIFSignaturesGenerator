package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename DownloadHTML.java
 */
public class DownloadHTML {
	
	public static int downloadHTML(String username) {
		int ifDownloadSuccess = 1;;
		try {
			URL url = new URL("https://projectfly.co.uk/u/" + username);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			String line;
			
			urlConnection.addRequestProperty("User-Agent", 
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");

			int responsecode = urlConnection.getResponseCode();

			if (responsecode == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
				
				while (reader.readLine() != null) {
					try {
						File dest = new File(GetVariables.folderFile + File.separatorChar + "download.html");
						
						FileOutputStream fileOutputStream = new FileOutputStream(dest);
						OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
						
						while ((line = reader.readLine()) != null) {
							outputStreamWriter.write(line);
							outputStreamWriter.append("\r\n");
						}
						
						outputStreamWriter.close();
						fileOutputStream.close();
						
						ifDownloadSuccess = 0;
						
						RunLog.logAppend("--------------");
						RunLog.logAppend("下载完成");
					} catch (Exception e) {
						RunLog.logAppend("出现异常：" + e.getMessage());
						ShowDialogs.showErrorDialog(e);
					}
				}
			} else {
				String response = null;
				
				switch (responsecode) {
				case 404:
					response = "404 Not Found. Please check your username again.";
					break;
				case 502:
					response = "502 Bad Gateway. Please try again in a few minutes.";
					break;
				default:
					response = responsecode + "";
					break;
				}
				Exception e = new Exception("Server returned with an error response code: " + response + 
						"<br>If the same exception occurs repeatedly, contact the author for a solution.");
				
				RunLog.logAppend("出现异常：" + e.getMessage());
				ShowDialogs.showErrorDialog(e);
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		
		}
		RunLog.logAppend("--------------");
		return ifDownloadSuccess;
	}
}
