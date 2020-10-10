package tools;

import java.io.File;

import org.dtools.ini.BasicIniFile;
import org.dtools.ini.BasicIniSection;
import org.dtools.ini.IniFile;
import org.dtools.ini.IniFileReader;
import org.dtools.ini.IniFileWriter;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import ui.Dialogs;

/**
 * @author Xujiayao
 */
public class Variables {
	
	//版本号
	public static String version = "v2.0";
	
	//存放缓存的文件夹
	public static File dataFolder = new File(System.getProperty("user.home") + "/AppData/Roaming/Java Projects");
	
	//屏幕宽高
	public static double screenWidth;
	public static double screenHeight;
	
	//保存配置的 File
	public static File configFile;
	
	//保存配置的 IniFile
	public static IniFile ini;
	
	//应用程序配置变量
	public static String language = "中文";
	public static String checkUpdates = "true";
	public static String username = "";
	public static String password = "";
	
	//首选项保存后尚未应用的变量
	public static String saveLanguage = "中文";
	public static String saveCheckUpdates = "true";

	//存储头像的文件名
	public static String avatarFilePath;
	
	//解析过的 LoginAPI 返回的数据
	public static String[] loginData = new String[7];
	
	//解析过的 ProfileAPI 返回的数据
	public static String[] profileData = new String[7];
	
	//解析过的 LogbookAPI 返回的数据
	public static String[] logbookData = new String[7];
	
	//解析过的 PassportAPI 返回的数据
	public static String[] passportData = new String[7];
	
	public static void init() {
		try {
			//分析系统类别
			if (!System.getProperty("os.name").startsWith("Windows")) {
				Dialogs.showErrorDialog("Not supported operating system, exiting now.", true);
				
				System.exit(1);
			}
			
			//分析Windows版本（本程序只支持Windows 7或以上）
			String name = System.getProperty("os.name");
			
			if (!(name.equals("Windows 7") || name.equals("Windows 8") || name.equals("Windows 8.1") || name.equals("Windows 10"))) {
				Dialogs.showErrorDialog("Not supported operating system, exiting now.", true);
				
				System.exit(1);
			}
			
			//分析屏幕宽高
			Rectangle2D screen = Screen.getPrimary().getBounds();
			screenWidth = screen.getWidth();
			screenHeight = screen.getHeight();
			
			//分析文件夹是否存在
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
			
			dataFolder = new File(dataFolder.toString() + "/PFSignaturesGenerator");
			
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			Dialogs.showErrorDialog("A fatal error occurs, exiting now.", true);
			
			System.exit(1);
		}
		
		try {
			configFile = new File(dataFolder.toString() + "/config.ini");
			
			initIniFile();
			
			if (configFile.exists() && configFile.isFile()) {
				try {
					IniFileReader reader = new IniFileReader(ini, configFile);
					
					reader.read();
					
					for (int i = 0; i < ini.getNumberOfSections(); i++) {
						for (IniItem item : ini.getSection(i).getItems()) {
							switch (item.getName()) {
							case "CheckUpdates":
								if (item.getValue().equals("false"))
									checkUpdates = "false";
								break;
							case "Language":
								if (item.getValue().equals("English"))
									language = "English";
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
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
			} else {
				try {
					IniSection preferences = new BasicIniSection("Preferences");
					ini.addSection(preferences);
					
					IniItem checkUpdates = new IniItem("CheckUpdates");
					checkUpdates.setValue(true);
					preferences.addItem(checkUpdates);

					IniItem language = new IniItem("Language");
					language.setValue("中文");
					preferences.addItem(language);

					IniSection variables = new BasicIniSection("Variables");
					ini.addSection(variables);
					
					IniItem username = new IniItem("Username");
					username.setValue("");
					variables.addItem(username);
					
					IniItem password = new IniItem("Password");
					password.setValue("");
					variables.addItem(password);
					
					IniFileWriter writer = new IniFileWriter(ini, configFile);
					writer.setIncludeSpaces(true);

					writer.write();
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
		}
	}
	
	public static boolean clearCaches() {
		try {
			System.gc();
			
			if (!dataFolder.exists()) {
				return true;
			}
			
			if (dataFolder.isDirectory()) {
				File[] files = dataFolder.listFiles();
				
				if(files == null) {
					dataFolder.delete();
				} else {
					for (int i = 0; i < files.length; i++) {
						deleteDir(files[i].getAbsolutePath());
					}
					
					dataFolder.delete();
					
					if (!dataFolder.exists()) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
		}
		
		return false;
	}

	public static void deleteDir(String dirPath) {
		File file = new File(dirPath);
		
		if(file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			
			if(files == null) {
				file.delete();
			} else {
				for (int i = 0; i < files.length; i++) {
					deleteDir(files[i].getAbsolutePath());
				}
				
				file.delete();
			}
		}
	}
	
	public static void saveVariables() {
		try {
			File dataFolder1 = new File(System.getProperty("user.home") + "/AppData/Roaming/Java Projects");
			
			//分析文件夹是否存在
			if (!dataFolder1.exists() || !dataFolder1.isDirectory()) {
				dataFolder1.mkdir();
			}
			
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
			
			try {
				configFile = new File(dataFolder.toString() + "/config.ini");
				
				initIniFile();
				
				try {
					IniSection preferences = new BasicIniSection("Preferences");
					ini.addSection(preferences);
					
					IniItem checkUpdates = new IniItem("CheckUpdates");
					checkUpdates.setValue(Variables.saveCheckUpdates);
					preferences.addItem(checkUpdates);

					IniItem language = new IniItem("Language");
					language.setValue(Variables.saveLanguage);
					preferences.addItem(language);

					IniSection variables = new BasicIniSection("Variables");
					ini.addSection(variables);
					
					IniItem username = new IniItem("Username");
					username.setValue(Variables.username);
					variables.addItem(username);
					
					IniItem password = new IniItem("Password");
					password.setValue(Variables.password);
					variables.addItem(password);
					
					IniFileWriter writer = new IniFileWriter(ini, configFile);
					writer.setIncludeSpaces(true);

					writer.write();
				} catch (Exception e) {
					Platform.runLater(() -> {
						Dialogs.showExceptionDialog(e);
					});
				}
			} catch (Exception e) {
				Platform.runLater(() -> {
					Dialogs.showExceptionDialog(e);
				});
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
	}
	
	public static void initIniFile() {
		ini = null;
		ini = new BasicIniFile(false);
	}
}
