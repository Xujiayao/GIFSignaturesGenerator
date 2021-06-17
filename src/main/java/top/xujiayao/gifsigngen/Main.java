package top.xujiayao.gifsigngen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import top.xujiayao.gifsigngen.tools.ConfigManager;
import top.xujiayao.gifsigngen.tools.HitokotoAPI;
import top.xujiayao.gifsigngen.tools.Update;
import top.xujiayao.gifsigngen.tools.Variables;
import top.xujiayao.gifsigngen.ui.Dialogs;
import top.xujiayao.gifsigngen.ui.LoginUI;
import top.xujiayao.gifsigngen.ui.MainUI;
import top.xujiayao.gifsigngen.ui.Panes;
import top.xujiayao.gifsigngen.ui.SystemTray;

/**
 * @author Xujiayao
 */
public class Main extends Application {

	public static SystemTray systemTray;
	public static Update update;
	public static Stage stage;
	public static LoginUI loginUI;
	public static MainUI mainUI;
	public static Panes panes;

	private static void checkError() {
		StringBuilder error = new StringBuilder("发生致命错误，程序将立即退出。\n\n");

		try {
			// 检查字体是否已安装
			for (String font : Variables.FONTS) {
				if (!Font.getFamilies().contains(font)) {
					error.append("-> 找不到 ").append(font).append(" 字体\n");
				}
			}

			// 分析操作系统（本程序只支持 Windows 7 或以上）
			double os = Double.parseDouble(System.getProperty("os.version"));

			if (os < 6.1) {
				error.append("-> 本程序不支持 ").append(System.getProperty("os.name")).append("（只支持 Windows 7 或以上）\n");
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
		}

		if (error.indexOf("->") != -1) {
			error.append("\n如果你认为这是一个错误，请告诉我。");

			Dialogs.showErrorDialog("发生致命错误", error.toString());

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
		stage.initStyle(StageStyle.UNDECORATED);

		stage.setOnCloseRequest(e -> {
			stage.close();
			Platform.exit();
			System.exit(0);
		});

		try {
			Main.stage = stage;

			checkError();

			Variables.init();

			ConfigManager.initConfig();

			loginUI = new LoginUI();
			loginUI.start(Main.stage);

			new Thread(new HitokotoAPI()).start();

			if (java.awt.SystemTray.isSupported()) {
				systemTray = new SystemTray();
				new Thread(systemTray).start();
			}

			if (ConfigManager.config.preferences.checkUpdates) {
				update = new Update();
				new Thread(update).start();
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			System.exit(1);
		}
	}
}
