package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import net.sf.image4j.codec.ico.ICODecoder;
import org.dtools.ini.BasicIniFile;
import org.dtools.ini.BasicIniSection;
import org.dtools.ini.IniFile;
import org.dtools.ini.IniFileReader;
import org.dtools.ini.IniFileWriter;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class Variables {

	// 软件版本
	public static String version = "v2.0.0";

	// 软件使用到的字体
	public static String[] fonts = {"Microsoft YaHei"};

	// 是否每次启动时检查更新
	public static boolean checkUpdates = true;

	// 登录方式
	public static String loginType = "projectFLY";

	// 用户名和密码
	public static String username = "";
	public static String password = "";

	// 屏幕宽度和高度
	public static double screenWidth;
	public static double screenHeight;

	// 存放缓存的文件夹
	public static File dataFolder;

	// 软件图标
	public static List<BufferedImage> icons;

	// 用户头像
	public static BufferedImage avatar;

	// 更新功能使用的文件 version.json 的下载路径
	public static String checkUpdateLink = "https://cdn.jsdelivr.net/gh/Xujiayao147/GIFSignaturesGenerator/update/version.json";

	public static void init() {
		StringBuilder error = new StringBuilder("发生致命错误，程序将立即退出。\n\n");

		// 分析屏幕宽高
		try {
			Rectangle2D screen = Screen.getPrimary().getBounds();
			screenWidth = screen.getWidth();
			screenHeight = screen.getHeight();

			if ((screenWidth == 0) || (screenHeight == 0))
				throw new Exception("Custom: bounds are equal to 0");
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法获取屏幕宽高\n");
		}

		// 分析文件夹是否存在并按需创建文件夹
		try {
			dataFolder = new File(System.getenv("APPDATA") + "/Java Projects");

			if (!dataFolder.exists() || !dataFolder.isDirectory())
				if (!dataFolder.mkdir())
					throw new Exception("Custom: the folder can't be created");

			dataFolder = new File(dataFolder.toString() + "/GIFSignaturesGenerator");

			if (!dataFolder.exists() || !dataFolder.isDirectory())
				if (!dataFolder.mkdir())
					throw new Exception("Custom: the folder can't be created");
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法分析文件夹是否存在或创建文件夹\n");
		}

		// 加载配置文件
		try {
			File config = new File(dataFolder.toString() + "/config.ini");
			IniFile ini = new BasicIniFile(false);

			if (config.exists() && config.isFile()) {
				new IniFileReader(ini, config).read();

				for (int i = 0; i < ini.getNumberOfSections(); i++) {
					for (IniItem item : ini.getSection(i).getItems()) {
						switch (item.getName()) {
							case "CheckUpdates" -> {
								if (item.getValue().equals("false"))
									checkUpdates = false;
							}
							case "LoginType" -> {
								if (item.getValue().equals("哔哩哔哩"))
									loginType = item.getValue();
							}
							case "Username" -> username = item.getValue();
							case "Password" -> password = item.getValue();
						}
					}
				}
			} else {
				IniSection preferences = new BasicIniSection("Preferences");
				ini.addSection(preferences);

				IniItem checkUpdates = new IniItem("CheckUpdates");
				checkUpdates.setValue(true);
				preferences.addItem(checkUpdates);

				IniSection variables = new BasicIniSection("Variables");
				ini.addSection(variables);

				IniItem loginType = new IniItem("LoginType");
				loginType.setValue("projectFLY");
				variables.addItem(loginType);

				IniItem username = new IniItem("Username");
				username.setValue("");
				variables.addItem(username);

				IniItem password = new IniItem("Password");
				password.setValue("");
				variables.addItem(password);

				IniFileWriter writer = new IniFileWriter(ini, config);
				writer.setIncludeSpaces(true);

				writer.write();
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载配置文件\n");
		}

		// 加载图标
		try {
			icons = ICODecoder.read(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.ico"));
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载图标\n");
		}

		// 加载默认头像
		try {
			avatar = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("no-profile-image.png")));
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载默认头像\n");
		}

		if (error.indexOf("->") != -1) {
			error.append("\n如果你认为这是一个错误，请告诉我。");

			Dialogs.showErrorDialog("发生致命错误", error.toString());

			System.exit(1);
		}
	}

	public static void saveConfig() {
		try {
			File config = new File(dataFolder.toString() + "/config.ini");
			IniFile ini = new BasicIniFile(false);

			IniSection preferences = new BasicIniSection("Preferences");
			ini.addSection(preferences);

			IniItem checkUpdates = new IniItem("CheckUpdates");
			checkUpdates.setValue(Variables.checkUpdates);
			preferences.addItem(checkUpdates);

			IniSection variables = new BasicIniSection("Variables");
			ini.addSection(variables);

			IniItem loginType = new IniItem("LoginType");
			loginType.setValue(Variables.loginType);
			variables.addItem(loginType);

			IniItem username = new IniItem("Username");
			username.setValue(Variables.username);
			variables.addItem(username);

			IniItem password = new IniItem("Password");
			password.setValue(Variables.password);
			variables.addItem(password);

			IniFileWriter writer = new IniFileWriter(ini, config);
			writer.setIncludeSpaces(true);

			writer.write();
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			Dialogs.showErrorDialog("发生错误", "无法保存新设置。");
		}
	}

	// ProjectFly 需要使用的变量
	public static class ProjectFly {
		// 解析过的 LoginAPI 返回的数据
		public static String[] loginData;
	}
}
