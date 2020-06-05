package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename GetVariables.java
 */
public class GetVariables {
	
	private static String osName = System.getProperty("os.name");
	public static String osType;
	
	public static String username = "";
	
	public static String folderFile = new String(System.getProperty("user.home") + File.separatorChar + "Appdata" + File.separatorChar + "Roaming" + 
			File.separatorChar + "Java Projects" + File.separatorChar + "GenerateProjectFlySignatures");
	
	//public static String folderFile = new String(System.getProperty("user.home") + File.separatorChar + "Library" + File.separatorChar + "Caches" + 
	//		File.separatorChar + "Java Projects" + File.separatorChar + "GenerateProjectFlySignatures");
	
	public static String returnedMessage = "";
	
	public static String displayName;
	public static String totalHours;
	public static String totalFlights;
	public static String biography;
	public static String favAircraft;
	public static String favAirport;
	public static String favRoute;
	public static String visitedCountries;
	public static String avgLdgRate;
	
	public static String success = "";
	public static String code = "";
	public static String message = "";
	public static String imageUrl = "";
	public static String deleteUrl = "";
	
	/**
	 * @method main
	 * @usage 启动此类
	 */
	public static void runGetVariables() {
		try {
			if (osName.startsWith("Windows")) {
				osType = "Windows";
				
				folderFile = new String(System.getProperty("user.home") + File.separatorChar + "Appdata" + File.separatorChar + "Roaming" + File.separatorChar + 
						"Java Projects" + File.separatorChar + "GenerateProjectFlySignatures");
				
				File fileNameFile = new File(folderFile + File.separatorChar + "username.txt");
				
				if (fileNameFile.exists() && fileNameFile.isFile()) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameFile), StandardCharsets.UTF_8));
					
					username = bufferedReader.readLine();
					bufferedReader.close();
				}
			} else if (osName.startsWith("Mac OS")) {
				osType = "Mac OS";
				
				folderFile = new String(System.getProperty("user.home") + File.separatorChar + "Library" + File.separatorChar + "Caches" + File.separatorChar + 
						"Java Projects" + File.separatorChar + "GenerateProjectFlySignatures");
				
				File fileNameFile = new File(folderFile + File.separatorChar + "username.txt");
				
				if (fileNameFile.exists() && fileNameFile.isFile()) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameFile), StandardCharsets.UTF_8));
					
					username = bufferedReader.readLine();
					bufferedReader.close();
				}
			} else {
				Exception e = new Exception("Not supported operating system");
				ShowDialogs.showErrorDialog(e);
				
				System.exit(0);
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
	}
}
