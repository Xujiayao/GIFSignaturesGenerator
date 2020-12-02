package tools;

import javafx.application.Platform;
import ui.Dialogs;

/**
 * @author Xujiayao
 */
public class ParseJSON {
	
	public static boolean parseProfileJSON(String data) {
		try {
			String[] datas = new String[7];
			
			data = data.substring(data.indexOf("\"bio\":") + 7);
			datas[0] = unicodeToString(data.substring(0, data.indexOf("\"")));
			
			datas[1] = String.valueOf(findStrOccurrence("\"complete\":true", data)) + " / " + String.valueOf(findStrOccurrence("\"complete\":", data));
			
			data = data.substring(data.indexOf("total_flights\"") + 16);
			datas[2] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("total_hours\"") + 14);
			datas[3] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("registration\"") + 15);
			datas[4] = unicodeToString(data.substring(0, data.indexOf("\"")));
			
			data = data.substring(data.indexOf("aircraft_type\"") + 16);
			datas[5] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("average_landing_rate\"") + 23);
			datas[6] = data.substring(0, data.indexOf("\""));
			
			Variables.profileData = datas;
			
			return true;
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return false;
	}
	
	public static boolean parseLoginJSON(String data) {
		try {
			String[] datas = new String[7];
			
			data = data.substring(44);
			datas[0] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("display_name\"") + 15);
			datas[1] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("avatar\"") + 9);
			datas[2] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("account_type\"") + 23);
			datas[3] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("roles\"") + 17);
			datas[4] = data.substring(0, data.indexOf(" "));
			
			data = data.substring(data.indexOf("colour\"") + 9);
			datas[5] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("token\"") + 8);
			datas[6] = data.substring(0, data.indexOf("\""));
			
			Variables.loginData = datas;
			
			return true;
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return false;
	}
	
	public static int findStrOccurrence(String findStr, String src) {
		int index = 0;
        int count = 0;
		
		try {
			if (!findStr.contains(" ")) {
	        	while((index = src.indexOf(findStr, index)) != -1) {
	        		count++;
	                index = index + findStr.length();
	            }
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
        return count;
    }
	
	public static String unicodeToString(String unicode) {
		StringBuffer buffer = new StringBuffer();
		
		try {
			int maxLoop = unicode.length();  
			
			for (int i = 0; i < maxLoop; i++) {
				if (unicode.substring(i).startsWith("\\n")) {
					i++;
					continue;
				} else if (unicode.charAt(i) == '\\') {  
					if ((i < maxLoop - 5) && ((unicode.charAt(i + 1) == 'u') || (unicode.charAt(i + 1) == 'U'))) {
						try {  
							buffer.append((char) Integer.parseInt(  
									unicode.substring(i + 2, i + 6), 16));  
							i += 5;  
						} catch (NumberFormatException e) {  
							buffer.append(unicode.charAt(i));  
						}
					}
				} else {  
					buffer.append(unicode.charAt(i));  
				}  
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return buffer.toString();
	}
}
