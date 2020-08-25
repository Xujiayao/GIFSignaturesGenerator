package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import log.RunLog;
import tools.GetVariables;
import tools.ShowDialogs;
import ui.FirstUI;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package main
 * @filename RunMain.java
 */
public class RunMain {

	/**
	 * @method main
	 * @usage 应用程序入口
	 */
	public static void main(String[] args) {
		GetVariables.runGetVariables();
		
		RunLog.main(null);
		
		FirstUI firstUI = new FirstUI();
		firstUI.setVisible(true);
		addWindowClosingEvent(firstUI);
	}
	
	/**
	 * @method addWindowClosingEvent
	 * @usage 添加关闭程序确认对话框事件
	 */
	public static void addWindowClosingEvent(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent we) { 
            	int confirm = ShowDialogs.showConfirmQuitDialog();
            	
            	RunLog.logAppend("JOptionPane创建完成");
				RunLog.logAppend("--------------");
				
				if (confirm == 0) {
					RunLog.logAppend("正在退出程序...");
					RunLog.logAppend("--------------");
					System.exit(0);
				}
            } 
        });
	}
}
