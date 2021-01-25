package io.gitee.xujiayao147.gifSignaturesGenerator;

import io.gitee.xujiayao147.gifSignaturesGenerator.tools.ParseJSON;
import io.gitee.xujiayao147.gifSignaturesGenerator.tools.Update;
import io.gitee.xujiayao147.gifSignaturesGenerator.tools.Variables;
import io.gitee.xujiayao147.gifSignaturesGenerator.ui.Dialogs;
import io.gitee.xujiayao147.gifSignaturesGenerator.ui.LoginUI;
import io.gitee.xujiayao147.gifSignaturesGenerator.ui.SystemTray;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Xujiayao
 */
public class Main extends Application {

	public static Variables variables;
	public static Dialogs dialogs;
	public static LoginUI loginUI;
	public static SystemTray systemTray;
	public static Update update;
	public static ParseJSON parseJSON;

	public static Stage stage;

	private static void checkError() {
		StringBuilder error = new StringBuilder("发生致命错误，程序将立即退出。\n\n");

		try {
			// 检查字体是否已安装
			if (!Font.getFamilies().contains(variables.font))
				error.append("-> 找不到微软雅黑字体\n");

			// 分析操作系统（本程序只支持Windows 7或以上）
			String os = System.getProperty("os.name");

			if (!(os.equals("Windows 7") || os.equals("Windows 8") || os.equals("Windows 8.1") || os.equals("Windows 10")))
				error.append("-> 本程序不支持 ").append(os).append("（只支持 Windows 7 或以上）\n");
		} catch (Exception e) {
			dialogs.showExceptionDialog(e);
		}

		if (error.indexOf("->") != -1) {
			error.append("\n如果你认为这是一个错误，请告诉我。");

			dialogs.showErrorDialog("发生致命错误", error.toString());

			System.exit(1);
		}
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void start(Stage stage) {
		variables = new Variables();
		dialogs = new Dialogs();
		loginUI = new LoginUI();
		systemTray = new SystemTray();
		update = new Update();
		parseJSON = new ParseJSON();

		try {
			Main.stage = stage;

			checkError();

			variables.init();

			loginUI.start(Main.stage);

			if (java.awt.SystemTray.isSupported())
				new Thread(systemTray).start();

			if (variables.checkUpdates)
				new Thread(update).start();
		} catch (Exception e) {
			dialogs.showExceptionDialog(e);
			System.exit(1);
		}
	}
}
