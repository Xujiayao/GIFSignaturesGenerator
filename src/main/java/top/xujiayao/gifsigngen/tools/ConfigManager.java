package top.xujiayao.gifsigngen.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import top.xujiayao.gifsigngen.ui.Dialogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Xujiayao
 */
public class ConfigManager {

	public static Config config;

	private static File file;

	public static void initConfig() {
		file = new File(Variables.dataFolder + "/config.json");

		if (file.exists() && file.length() != 0) {
			config = loadConfig();
		} else {
			config = createConfig();
		}
	}

	private static Config loadConfig() {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));

			String temp;
			StringBuilder jsonString = new StringBuilder();

			while ((temp = reader.readLine())!= null) {
				jsonString.append(temp);
			}

			reader.close();

			return new Gson().fromJson(jsonString.toString(), new TypeToken<Config>() {
			}.getType());
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			Dialogs.showErrorDialog("发生错误", "无法加载配置文件。");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new Config();
	}

	private static Config createConfig() {
		Config config = new Config();

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));

			String jsonString = new GsonBuilder()
				  .setPrettyPrinting()
				  .disableHtmlEscaping()
				  .create()
				  .toJson(config);

			writer.write(jsonString);
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			Dialogs.showErrorDialog("发生错误", "无法创建配置文件。");
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return config;
	}

	public static void updateConfig() {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));

			String jsonString = new GsonBuilder()
				  .setPrettyPrinting()
				  .disableHtmlEscaping()
				  .create()
				  .toJson(config);

			writer.write(jsonString);
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			Dialogs.showErrorDialog("发生错误", "无法保存配置文件。");
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
