package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

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
	
	//保存的变量文件夹
	public static File languageFile;
	public static File checkUpdatesFile;
	public static File usernameFile;
	public static File passwordFile;
	
	//应用程序配置变量
	public static String language = "中文";
	public static String checkUpdates = "true";
	public static String username = "";
	public static String password = "";

	//存储头像的文件名
	public static String avatarFilePath;
	
	//解析过的 LoginAPI 返回的数据
	public static String[] loginDatas = new String[7];
	
	//解析过的 ProfileAPI 返回的数据
	public static String[] profileDatas = new String[7];
	
	//解析过的 LogbookAPI 返回的数据
	public static String[] logbookDatas = new String[7];
	
	//解析过的 PassportAPI 返回的数据
	public static String[] passportDatas = new String[7];
	
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
			languageFile = new File(dataFolder.toString() + "/language.txt");
			checkUpdatesFile = new File(dataFolder.toString() + "/checkUpdates.txt");
			usernameFile = new File(dataFolder.toString() + "/username.txt");
			passwordFile = new File(dataFolder.toString() + "/password.txt");
			
			//应用程序语言
			if (languageFile.exists() && languageFile.isFile()) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(languageFile), StandardCharsets.UTF_8));
				
				try {
					if (bufferedReader.readLine().equals("English")) {
						language = "English";
					}
				} catch (Exception e) {
					OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(languageFile), StandardCharsets.UTF_8);
					osw.write("中文");
					osw.flush();
					osw.close();
				}
				
				bufferedReader.close();
			} else {
				languageFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(languageFile), StandardCharsets.UTF_8);
				osw.write("中文");
				osw.flush();
				osw.close();
			}
			
			//是否在每次启动时检查更新
			if (checkUpdatesFile.exists() && checkUpdatesFile.isFile()) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(checkUpdatesFile), StandardCharsets.UTF_8));
				
				try {
					if (bufferedReader.readLine().equals("false")) {
						checkUpdates = "false";
					}
				} catch (Exception e) {
					OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(checkUpdatesFile), StandardCharsets.UTF_8);
					osw.write("true");
					osw.flush();
					osw.close();
				}
				
				bufferedReader.close();
			} else {
				checkUpdatesFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(checkUpdatesFile), StandardCharsets.UTF_8);
				osw.write("true");
				osw.flush();
				osw.close();
			}
			
			//用户名
			if (usernameFile.exists() && usernameFile.isFile()) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(usernameFile), StandardCharsets.UTF_8));
				
				username = bufferedReader.readLine();
				
				bufferedReader.close();
			} else {
				usernameFile.createNewFile();
			}
			
			//密码
			if (passwordFile.exists() && passwordFile.isFile()) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(passwordFile), StandardCharsets.UTF_8));
				
				password = bufferedReader.readLine();
				
				bufferedReader.close();
			} else {
				passwordFile.createNewFile();
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
	
	public static void savePreferences(String language, String checkUpdates) {
		try {
			File dataFolder1 = new File(System.getProperty("user.home") + "/AppData/Roaming/Java Projects");
			
			//分析文件夹是否存在
			if (!dataFolder1.exists() || !dataFolder1.isDirectory()) {
				dataFolder1.mkdir();
			}
			
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
			
			//应用程序语言
			if (languageFile.exists() && languageFile.isFile()) {
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(languageFile), StandardCharsets.UTF_8);
				osw.write(language);
				osw.flush();
				osw.close();
			} else {
				languageFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(languageFile), StandardCharsets.UTF_8);
				osw.write(language);
				osw.flush();
				osw.close();
			}
			
			//是否在每次启动时检查更新
			if (checkUpdatesFile.exists() && checkUpdatesFile.isFile()) {
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(checkUpdatesFile), StandardCharsets.UTF_8);
				osw.write(checkUpdates);
				osw.flush();
				osw.close();
			} else {
				checkUpdatesFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(checkUpdatesFile), StandardCharsets.UTF_8);
				osw.write(checkUpdates);
				osw.flush();
				osw.close();
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
		}
	}
	
	public static void saveAccount(String username, String password) {
		try {
			File dataFolder1 = new File(System.getProperty("user.home") + "/AppData/Roaming/Java Projects");
			
			//分析文件夹是否存在
			if (!dataFolder1.exists() || !dataFolder1.isDirectory()) {
				dataFolder1.mkdir();
			}
			
			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				dataFolder.mkdir();
			}
			
			//用户名
			if (usernameFile.exists() && usernameFile.isFile()) {
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(usernameFile), StandardCharsets.UTF_8);
				osw.write(username);
				osw.flush();
				osw.close();
			} else {
				usernameFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(usernameFile), StandardCharsets.UTF_8);
				osw.write(username);
				osw.flush();
				osw.close();
			}
			
			//密码
			if (passwordFile.exists() && passwordFile.isFile()) {
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(passwordFile), StandardCharsets.UTF_8);
				osw.write(password);
				osw.flush();
				osw.close();
			} else {
				passwordFile.createNewFile();
				
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(passwordFile), StandardCharsets.UTF_8);
				osw.write(password);
				osw.flush();
				osw.close();
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
	}
}
