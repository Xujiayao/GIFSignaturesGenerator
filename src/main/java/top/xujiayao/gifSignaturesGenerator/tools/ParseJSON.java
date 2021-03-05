package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

public class ParseJSON {

	/*
	0: username
	1: display_name
	2: avatar
	3: account_type
	4: roles
	5: colour
	6: token
	 */
	public static String[] parseLoginJSON(String data) {
		if (data == null) {
			return null;
		}

		String[] datas = new String[7];

		try {
			data = data.substring(44);
			datas[0] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("display_name\"") + 15);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("avatar\"") + 9);
			datas[2] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("account_type\"") + 23);
			datas[3] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("roles\"") + 17);
			datas[4] = data.substring(0, data.indexOf(" "));

			data = data.substring(data.indexOf("colour\"") + 9);
			datas[5] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("token\"") + 8);
			datas[6] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	/*
	0: version
	1: data
	2: link
	 */
	public static String[] parseUpdateJSON(String data) {
		String[] datas = new String[3];

		try {
			data = data.substring(12);
			datas[0] = data.substring(0, data.indexOf("\""));

			if (datas[0].equals(Variables.version)) {
				return null;
			}

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[2] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return datas;
	}
}
