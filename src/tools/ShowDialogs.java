package tools;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename ShowDialogs.java
 */
public class ShowDialogs {
	
	/**
	 * @method showErrorDialog
	 * @usage 弹出指定错误对话框
	 */
	public static void showErrorDialog(Exception e) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JOptionPane.showMessageDialog(null, "<html>Exception occurs: " + e + "<br><br>"
					+ "出现异常：" + e + "</html>", "Exception 异常", JOptionPane.ERROR_MESSAGE);
			
			RunLog.logAppend("JOptionPane创建完成");
			RunLog.logAppend("--------------");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "<html>Exception occurs: " + e1 + "<br><br>"
					+ "出现异常：" + e1 + "</html>", "Exception 异常", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "<html>Exception occurs: " + e + "<br><br>"
					+ "出现异常：" + e + "</html>", "Exception 异常", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * @method showMessageDialog
	 * @usage 弹出消息对话框
	 */
	public static void showMessageDialog(String text) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JOptionPane.showMessageDialog(null, "<html>" + text + "</html>", "Message 消息", JOptionPane.OK_OPTION);
			
			RunLog.logAppend("JOptionPane创建完成");
			RunLog.logAppend("--------------");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "<html>Exception occurs: " + e1 + "<br><br>"
					+ "出现异常：" + e1 + "</html>", "Exception 异常", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "<html>" + text + "</html>", "Message 消息", JOptionPane.OK_OPTION);
		}
	}
	
	/**
	 * @method showConfirmQuitDialog
	 * @usage 弹出确认退出对话框
	 */
	public static int showConfirmQuitDialog() {
		int confirm = 0;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			confirm = JOptionPane.showConfirmDialog(null, "<html>Exit the application?<br><br>"
					+ "\u60A8\u786E\u5B9A\u8981\u9000\u51FA\u5417\uFF1F</html>", "Confirm 确认", JOptionPane.YES_NO_OPTION);
			
			RunLog.logAppend("JOptionPane创建完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e);
			JOptionPane.showMessageDialog(null, "<html>Exception occurs: " + e + "<br><br>"
					+ "出现异常：" + e + "</html>", "Exception 异常", JOptionPane.ERROR_MESSAGE);
		}
		
		return confirm;
	}
}
