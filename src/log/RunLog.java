package log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import tools.GetVariables;
import tools.ShowDialogs;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package log
 * @filename RunLog.java
 */
public class RunLog {
	
	/**
	 * @method main
	 * @usage 入口
	 */
	public static void main(String[] args) {
		try {
			logAppend("-----------------------------------------");
			logAppend("应用程序已启动");
			logAppend("--------------");
			logAppend("日志程序已启动");
			logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
	}
	
	/**
	 * @method logAppend
	 * @usage 往日志文件写入信息
	 */
	public static void logAppend(String text) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			
			File folder = null;
			File folder1 = null;
			File file = null;
			
			if (GetVariables.osType == "Windows") {
				String folderName = System.getProperty("user.home") + File.separatorChar + "Appdata" + File.separatorChar + "Roaming" + File.separatorChar + 
						"Java Projects";
				String folderName1 = folderName + File.separatorChar + "GenerateProjectFlySignatures";
				
				folder = new File(folderName);
				folder1 = new File(folderName1);
				
				if (!folder.exists() || !folder.isDirectory())
					folder.mkdir();
				
				if (!folder1.exists() || !folder1.isDirectory())
					folder1.mkdir();
				
				file = new File(folderName1 + File.separatorChar + "log.txt");
				
				if (!file.exists() || file.isDirectory()) {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file, true);
					OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
					
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "日志文件夹创建完成" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "日志文件创建完成" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					
					writer.close();
				}
				
				FileOutputStream fos = new FileOutputStream(file, true);
				OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
				
				writer.write("[" + sdf.format(date) + "] " + text + "\r\n");
				
				writer.close();
			} else if (GetVariables.osType == "Mac OS") {
				String folderName = System.getProperty("user.home") + File.separatorChar + "Library" + File.separatorChar + "Caches" + File.separatorChar + 
						"Java Projects";
				String folderName1 = folderName + File.separatorChar + "GenerateProjectFlySignatures";
				
				folder = new File(folderName);
				folder1 = new File(folderName1);
				
				if (!folder.exists() || !folder.isDirectory())
					folder.mkdir();
				
				if (!folder1.exists() || !folder1.isDirectory())
					folder1.mkdir();
				
				file = new File(folderName1 + File.separatorChar + "log.txt");
				
				if (!file.exists() || file.isDirectory()) {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file, true);
					OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
					
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "日志文件夹创建完成" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "日志文件创建完成" + "\r\n");
					writer.write("[" + sdf.format(date) + "] " + "--------------" + "\r\n");
					
					writer.close();
				}
				
				FileOutputStream fos = new FileOutputStream(file, true);
				OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
				
				writer.write("[" + sdf.format(date) + "] " + text + "\r\n");
				
				writer.close();
			}
		} catch (Exception e) {
			ShowDialogs.showErrorDialog(e);
			RunLog.logAppend("出现异常：" + e.getMessage());
		}
	}
}
