package io.gitee.xujiayao147.gifSignaturesGenerator.tools;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import net.sf.image4j.codec.ico.ICODecoder;
import org.apache.commons.io.FileUtils;
import org.dtools.ini.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Xujiayao
 */
public class Variables {

	// 软件版本
	public String version = "v2.0.0";

	// 界面的字体
	public String font = "Microsoft YaHei";

	// 是否每次启动时检查更新
	public boolean checkUpdates = true;

	// 用户名和密码
	public String username = "";
	public String password = "";

	// 屏幕宽度和高度
	public double screenWidth;
	public double screenHeight;

	// 存放缓存的文件夹
	public File dataFolder;

	// 软件图标
	public List<BufferedImage> icons;

	public void init() {
		StringBuilder error = new StringBuilder("发生致命错误，程序将立即退出。\n\n");

		// 分析屏幕宽高
		try {
			Rectangle2D screen = Screen.getPrimary().getBounds();
			screenWidth = screen.getWidth();
			screenHeight = screen.getHeight();

			if ((screenWidth == 0) || (screenHeight == 0))
				throw new Exception("Custom: bounds are equal to 0");
		} catch (Exception e) {
			Main.dialogs.showExceptionDialog(e);
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
			Main.dialogs.showExceptionDialog(e);
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
							case "CheckUpdates":
								if (item.getValue().equals("false"))
									checkUpdates = false;
								break;
							case "Username":
								username = item.getValue();
								break;
							case "Password":
								password = item.getValue();
								break;
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
			Main.dialogs.showExceptionDialog(e);
			error.append("-> 无法加载配置文件\n");
		}

		// 加载图标
		try {
			icons = ICODecoder.read(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.ico"));
		} catch (Exception e) {
			Main.dialogs.showExceptionDialog(e);
			error.append("-> 无法加载图标\n");
		}

		if (error.indexOf("->") != -1) {
			error.append("\n如果你认为这是一个错误，请告诉我。");

			Main.dialogs.showErrorDialog("发生致命错误", error.toString());

			System.exit(1);
		}
	}
}
