package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import javafx.application.Platform;

public class ParseJSON {

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
