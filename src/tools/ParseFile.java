package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename ParseFile.java
 */
public class ParseFile {
	
	/**
	 * @method parseFile
	 * @usage 解析文件
	 */
	public static String parseFile(String text, int deductLine, int tryNum, int subStringNum, int setLine) {
		String string = "N/A";
		int line;
		
		try {
			RunLog.logAppend("--------------");
			
			if (setLine == -1) {
				line = getLineOfText(text);
				
				if (line != -1) {
					String txt = getTextOfLine(line + deductLine);
					
					if (!(txt == "Exception")) {
						String strPre = txt.substring(subStringNum);
						string = strPre.substring(0, strPre.indexOf("<"));
						
						RunLog.logAppend("解析结果" + tryNum + "：");
						RunLog.logAppend(string);
					}
				}
			} else {				
				String txt = getTextOfLine(setLine);
								
				if (txt != "Exception") {
					String strPre = txt.substring(subStringNum);
					string = strPre.substring(0, strPre.indexOf("<"));
					
					RunLog.logAppend("解析结果" + tryNum + "：");
					RunLog.logAppend(string);
				}
			}
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		if (string.contains("&rdquo;")) {
			string = string.replace("&rdquo;", "“");
		}
		if (string.contains("&ldquo;")) {
			string = string.replace("&ldquo;", "”");
		}
		
		return string;
	}
	
	/**
	 * @method getLineOfText
	 * @usage 获取文本所在的行数
	 */
	private static int getLineOfText(String text) {
		int line = 1;
		try {
			File file = new File(GetVariables.folderFile + File.separator + "download.html");

			BufferedReader br = new BufferedReader(new FileReader(file));
			String rl;
			
			while ((rl = br.readLine()) != null) {
				if (rl.contains(text)) {
					RunLog.logAppend("找到了文本：" + text + "所在的行数：" + line);
					break;
				}
				line++;
			}
			
			br.close();
			return line;
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return -1;
	}
	
	/**
	 * @method getTextOfLine
	 * @usage 获取该行的文本
	 */
	private static String getTextOfLine(int line) {
		String txt = "";
		try {
			File file = new File(GetVariables.folderFile + File.separatorChar + "download.html");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			LineNumberReader reader = new LineNumberReader(bufferedReader);
			
			int lines = 0;

			while (txt != null) {
				lines++;
				txt = reader.readLine();
				if (lines == line) {
					break;
				}
			}
			
			RunLog.logAppend("找到了第" + line + "行的内容：");
			RunLog.logAppend(txt);
			
			reader.close();
			bufferedReader.close();
			
			return txt;
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e);
			ShowDialogs.showErrorDialog(e);
			txt = "N/A";
		}
		return txt;
	}
	
	/**
	 * @method parseBiography
	 * @usage 解析个人简介
	 */
	public static String parseBiography() {
		String string = "";
		int line;
		
		try {
			RunLog.logAppend("--------------");
			
			line = getLineOfText("bio");
			
			if (line != -1) {
				String txt = getTextOfLine(line);
				
				if (txt != "Exception") {
					if (txt.contains("<li class=\"bio\"></li>") || txt.contains("<li class=\"bio\"><p></p></li>")) {
						RunLog.logAppend("解析结果4：");
						RunLog.logAppend(string);
					} else {
						String strPre = txt.substring(22);
						
						String strPreString;
						StringBuilder finalString;

						if (strPre.contains("</li>")) {
							string = strPre.substring(0, strPre.indexOf("<"));
						} else {
							strPreString = strPre.substring(0, strPre.indexOf("<"));

							finalString = new StringBuilder(strPreString);

							for (int i = 1; i < 10; i++) {
								txt = getTextOfLine(line + i);
								
								if (txt == "<br />") {
									finalString.append(" ");
									continue;
								}
								
								strPreString = txt.substring(0, txt.indexOf("<"));

								finalString.append(strPreString);
								
								if (txt.contains("</li>")) {
									break;
								}
							}
							string = finalString.toString();
							
							if (string.contains("&rdquo;")) {
								string = string.replace("&rdquo;", "“");
							}
							if (string.contains("&ldquo;")) {
								string = string.replace("&ldquo;", "”");
							}
						}
					}
				}
			}
			RunLog.logAppend("解析结果4：");
			RunLog.logAppend(string);
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return string;
	}
}
