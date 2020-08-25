package tools;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename ParseMessage.java
 */
public class ParseMessage {
	
	/**
	 * @method parse
	 * @usage 解析服务器返回的信息
	 */
	public static int parse() {
		String string = GetVariables.returnedMessage;
		
		String success = "";
		String code = "";
		String message = "";
		String imageUrl = "";
		String deleteUrl = "";
		
		try {
			{
				success = string.substring(11);
				success = success.substring(0, success.indexOf(","));
				
				RunLog.logAppend("Success: " + success);
				RunLog.logAppend("--------------");
			}
			string = string.substring(string.indexOf(",") + 1, string.length());
			{
				code = string.substring(8);
				code = code.substring(0, code.indexOf("\""));
				
				RunLog.logAppend("Code: " + code);
				RunLog.logAppend("--------------");
			}
			string = string.substring(string.indexOf(",") + 1, string.length());
			{
				message = string.substring(11);
				message = message.substring(0, message.indexOf("\""));
				
				if (message.contains("\\")) {
					message = message.replace("\\", "");
				}
				
				RunLog.logAppend("Message: " + message);
				RunLog.logAppend("--------------");
			}
			
			if (message.contains("Image upload repeated limit")) {
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				{
					imageUrl = string.substring(10);
					imageUrl = imageUrl.substring(0, imageUrl.indexOf("\""));
					
					if (imageUrl.contains("\\")) {
						imageUrl = imageUrl.replace("\\", "");
					}
					
					RunLog.logAppend("imageUrl: " + imageUrl);
					RunLog.logAppend("--------------");
				}
			} else if (message.contains("Upload success.")) {
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				string = string.substring(string.indexOf(",") + 1, string.length());
				{
					imageUrl = string.substring(7);
					imageUrl = imageUrl.substring(0, imageUrl.indexOf("\""));
					
					if (imageUrl.contains("\\")) {
						imageUrl = imageUrl.replace("\\", "");
					}
					
					RunLog.logAppend("imageUrl: " + imageUrl);
					RunLog.logAppend("--------------");
				}
				string = string.substring(string.indexOf(",") + 1, string.length());
				{
					deleteUrl = string.substring(10);
					deleteUrl = deleteUrl.substring(0, deleteUrl.indexOf("\""));
					
					if (deleteUrl.contains("\\")) {
						deleteUrl = deleteUrl.replace("\\", "");
					}
					
					RunLog.logAppend("imageUrl: " + imageUrl);
					RunLog.logAppend("--------------");
				}
			} else {
				Exception e = new Exception("<html>Unable to identify the message recieved.<br>无法辨认获取到的信息。</html>");
				RunLog.logAppend("无法辨认获取到的信息。");
				RunLog.logAppend("--------------");
				RunLog.logAppend(GetVariables.returnedMessage);
				RunLog.logAppend("--------------");
				ShowDialogs.showErrorDialog(e);
			}
			
			GetVariables.success = success;
			GetVariables.code = code;
			GetVariables.message = message;
			GetVariables.imageUrl = imageUrl;
			GetVariables.deleteUrl = deleteUrl;
			
			return 0;
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return 1;
	}
}
