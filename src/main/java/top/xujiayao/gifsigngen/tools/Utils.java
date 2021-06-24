package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import top.xujiayao.gifsigngen.ui.Dialogs;

/**
 * @author Xujiayao
 */
public class Utils {

	private Utils() {
		throw new IllegalStateException("工具类");
	}

	public static String getHttpResponse(HttpPost post, HttpGet get) {
		try {
			if (post != null) {
				Variables.response = Variables.httpClient.execute(post);
			} else {
				Variables.response = Variables.httpClient.execute(get);
			}

			if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = Variables.response.getEntity();

				return EntityUtils.toString(httpEntity);
			} else {
				Platform.runLater(() -> Dialogs.showExceptionDialog(new CustomException("服务器返回状态码："
					  + Variables.response.getStatusLine().getStatusCode())));
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return null;
	}

	public static int findStrOccurrence(String findStr, String src) {
		int index = 0;
		int count = 0;

		try {
			while ((index = src.indexOf(findStr, index)) != -1) {
				count++;
				index = index + findStr.length();
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return count;
	}

	public static String unicodeToString(String unicode) {
		StringBuilder builder = new StringBuilder();

		int maxLoop = unicode.length();

		for (int i = 0; i < maxLoop; i++) {
			if (unicode.startsWith("\\n", i)) {
				i++;
			} else if (unicode.charAt(i) == '\\') {
				if ((i < maxLoop - 5) && ((unicode.charAt(i + 1) == 'u') || (unicode.charAt(i + 1) == 'U'))) {
					try {
						builder.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException e) {
						builder.append(unicode.charAt(i));
					}
				}
			} else {
				builder.append(unicode.charAt(i));
			}
		}

		return builder.toString();
	}
}
