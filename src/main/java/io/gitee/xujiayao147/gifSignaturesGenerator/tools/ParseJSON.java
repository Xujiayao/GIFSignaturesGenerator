package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import javafx.application.Platform;

public class ParseJSON {

	public String[] parseLoginJSON(String data) {
		if (data == null)
			return null;

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
			Platform.runLater(() -> Main.dialogs.showExceptionDialog(e));
		}

		return datas;
	}

	public String[] parseUpdateJSON(String data) {
		String[] datas = new String[3];

		try {
			data = data.substring(12);
			datas[0] = data.substring(0, data.indexOf("\""));

			if (datas[0].equals(Main.variables.version))
				return null;

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[1] = data.substring(0, data.indexOf("\""));

			data = data.substring(data.indexOf("\"")).substring(10);
			datas[2] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> Main.dialogs.showExceptionDialog(e));
		}

		return datas;
	}
}
