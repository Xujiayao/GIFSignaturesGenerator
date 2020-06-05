package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename SaveVariablesFiles.java
 */
public class SaveVariablesFiles {
	
	/**
	 * @method saveVariable
	 * @usage 存储变量进指定文件
	 */
	public static void saveVariable(String filename, String text) {
		try {
			if (!text.equals("")) {
				String string = GetVariables.folderFile + File.separatorChar + filename;
				
				File file = new File(string);
				
				if (!file.exists() || !file.isFile())
					file.createNewFile();
				
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				osw.write(text);
				osw.flush();
				osw.close();
				
				GetVariables.runGetVariables();
			} else {
				Exception e = new Exception("Nothing in the textfield");
				RunLog.logAppend("出现异常：" + e);
				ShowDialogs.showErrorDialog(e);
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
	}
}
