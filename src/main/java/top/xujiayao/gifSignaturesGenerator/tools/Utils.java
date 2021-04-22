package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

/**
 * @author Xujiayao
 */
public class Utils {

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

		try {
			int maxLoop = unicode.length();

			for (int i = 0; i < maxLoop; i++) {
				if (unicode.substring(i).startsWith("\\n")) {
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
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return builder.toString();
	}
}
