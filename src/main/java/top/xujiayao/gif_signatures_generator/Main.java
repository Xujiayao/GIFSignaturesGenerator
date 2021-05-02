package top.xujiayao.gif_signatures_generator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import top.xujiayao.gif_signatures_generator.tools.HitokotoAPI;
import top.xujiayao.gif_signatures_generator.tools.Update;
import top.xujiayao.gif_signatures_generator.tools.Variables;
import top.xujiayao.gif_signatures_generator.ui.Dialogs;
import top.xujiayao.gif_signatures_generator.ui.LoginUI;
import top.xujiayao.gif_signatures_generator.ui.MainUI;
import top.xujiayao.gif_signatures_generator.ui.Panes;
import top.xujiayao.gif_signatures_generator.ui.SystemTray;

/**
 * @author Xujiayao
 */
public class Main extends Application {

	public static final HitokotoAPI hitokotoAPI = new HitokotoAPI();
	public static final SystemTray systemTray = new SystemTray();
	public static final Update update = new Update();
	public static final LoginUI loginUI = new LoginUI();

	private static Stage stage;
	private static MainUI mainUI;
	private static Panes panes;

	private static Variables.ProjectFly projectFlyData;

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
			String os = System.getProperty("os.name");

			if (!(os.equals("Windows 7") || os.equals("Windows 8") || os.equals("Windows 8.1") || os.equals("Windows 10"))) {
				error.append("-> 本程序不支持 ").append(os).append("（只支持 Windows 7 或以上）\n");
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

	public static Panes getPanes() {
		return panes;
	}

	public static void setPanes(Panes panes) {
		Main.panes = panes;
	}

	public static MainUI getMainUI() {
		return mainUI;
	}

	public static void setMainUI(MainUI mainUI) {
		Main.mainUI = mainUI;
	}

	public static Variables.ProjectFly getProjectFlyData() {
		return projectFlyData;
	}

	public static void setProjectFlyData(Variables.ProjectFly projectFlyData) {
		Main.projectFlyData = projectFlyData;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
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
			Main.setStage(stage);

			checkError();

			Variables.init();

			loginUI.start(Main.getStage());

			new Thread(hitokotoAPI).start();

			if (java.awt.SystemTray.isSupported()) {
				new Thread(systemTray).start();
			}

			if (Variables.checkUpdates) {
				new Thread(update).start();
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			System.exit(1);
		}
	}
}
