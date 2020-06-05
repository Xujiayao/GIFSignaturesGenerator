package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import log.RunLog;
import main.RunMain;
import tools.GetScreenSize;
import tools.ShowDialogs;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package ui
 * @filename WelcomeUI.java
 */
@SuppressWarnings("serial")
public class FirstUI extends JFrame {
	
	private JPanel contentPane;

	/**
	 * @method main
	 * @usage 启动此用户界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstUI frame = new FirstUI();
					frame.setVisible(true);
				} catch (Exception e) {
					RunLog.logAppend("出现异常：" + e.getMessage());
					ShowDialogs.showErrorDialog(e);
				}
			}
		});
	}
	
	public FirstUI() {
		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		
		setTitle("Generate projectFLY Signatures - By Xujiayao");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds((GetScreenSize.ScreenWidth - 800) / 2, (GetScreenSize.ScreenHeight - 600) / 2, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("Welcome \u6B22\u8FCE");
		label1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label1.setBounds(211, 10, 573, 28);
		contentPane.add(label1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(211, 48, 573, 1);
		contentPane.add(separator1);
		
		JLabel label2 = new JLabel("<html>With this application, you will be able to generate projectFLY forum signatures in a simple way.</html>");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label2.setBounds(211, 59, 573, 44);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("\u6B64\u5E94\u7528\u7A0B\u5E8F\u53EF\u4EE5\u8BA9\u60A8\u4EE5\u4E00\u79CD\u7B80\u5355\u7684\u65B9\u5F0F\u751F\u6210projectFLY\u7684\u8BBA\u575B\u7B7E\u540D\u56FE\u3002");
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label3.setBounds(211, 113, 583, 22);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("<html>This wizard leads you step by step to collect all required information to generate the signature.</html>");
		label4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label4.setBounds(211, 177, 573, 44);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("\u6B64\u5411\u5BFC\u5C06\u5F15\u5BFC\u60A8\u9010\u6B65\u6536\u96C6\u751F\u6210\u7B7E\u540D\u56FE\u6240\u9700\u7684\u6240\u6709\u4FE1\u606F\u3002");
		label5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label5.setBounds(211, 231, 573, 22);
		contentPane.add(label5);
		
		JButton button = new JButton("Cancel");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(211, 533, 89, 28);
		contentPane.add(button);
		
		JButton button2 = new JButton("Next ->");
		button2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button2.setBounds(691, 533, 93, 28);
		contentPane.add(button2);
		getRootPane().setDefaultButton(button2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 201, 571);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(200, 0, 1, 571);
		panel.add(separator);
		
		JLabel leftLabel1 = new JLabel("1. Welcome");
		leftLabel1.setBounds(10, 10, 180, 22);
		panel.add(leftLabel1);
		leftLabel1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		
		JLabel leftLabel2 = new JLabel("2. Download");
		leftLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel2.setBounds(10, 42, 180, 22);
		panel.add(leftLabel2);
		
		JLabel leftLabel3 = new JLabel("3. Parse");
		leftLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel3.setBounds(10, 74, 180, 22);
		panel.add(leftLabel3);
		
		JLabel leftLabel4 = new JLabel("4. Generate");
		leftLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel4.setBounds(10, 106, 180, 22);
		panel.add(leftLabel4);
		
		JLabel leftLabel5 = new JLabel("5. Finished");
		leftLabel5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel5.setBounds(10, 138, 180, 22);
		panel.add(leftLabel5);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(211, 521, 573, 1);
		contentPane.add(separator2);
		
		JLabel label6 = new JLabel("<html>The variables entered will be stored in (%Appdata%/Java Projects/***). When you open this application again, the stored variables will be filled in automatically. You may delete these temporary files in any time.</html>");
		label6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label6.setBounds(211, 295, 573, 66);
		contentPane.add(label6);
		
		JLabel label7 = new JLabel("<html>\u8F93\u5165\u7684\u53D8\u91CF\u5C06\u4F1A\u88AB\u5B58\u50A8\u5728\uFF08%Appdata%/Java Projects/***\uFF09\u4E2D\u3002\u518D\u6B21\u6253\u5F00\u6B64\u5E94\u7528\u7A0B\u5E8F\u65F6\uFF0C\u5E94\u7528\u7A0B\u5E8F\u5C06\u81EA\u52A8\u586B\u5145\u5DF2\u5B58\u50A8\u7684\u53D8\u91CF\u3002\u60A8\u53EF\u4EE5\u968F\u65F6\u5220\u9664\u8FD9\u4E9B\u4E34\u65F6\u6587\u4EF6\u3002</html>");
		label7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label7.setBounds(211, 371, 573, 44);
		contentPane.add(label7);
		
		JLabel lblThisApplicationIs = new JLabel("\u6B64\u5E94\u7528\u7A0B\u5E8F\u53EA\u517C\u5BB9macOS\u548CWindows\u3002");
		lblThisApplicationIs.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblThisApplicationIs.setBounds(211, 489, 573, 22);
		contentPane.add(lblThisApplicationIs);
		
		JLabel label5_1_1 = new JLabel("This application is only compatible with macOS and Windows.");
		label5_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label5_1_1.setBounds(211, 457, 573, 22);
		contentPane.add(label5_1_1);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int confirm = ShowDialogs.showConfirmQuitDialog();
				
				if (confirm == 0) {
					RunLog.logAppend("正在退出程序...");
					RunLog.logAppend("--------------");
					System.exit(0);
				}
			}
		});
		
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					int confirm = ShowDialogs.showConfirmQuitDialog();
					
					if (confirm == 0) {
						RunLog.logAppend("正在退出程序...");
						RunLog.logAppend("--------------");
						System.exit(0);
					}
				}
			}
		});
		
		button2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					RunLog.logAppend("正在从FirstUI跳转至SecondUI...");
					RunLog.logAppend("--------------");
					
					SecondUI secondUI = new SecondUI();
	            	secondUI.setVisible(true);
	            	RunMain.addWindowClosingEvent(secondUI);
					
					dispose();
				}
			}
		});
		
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				RunLog.logAppend("正在从FirstUI跳转至SecondUI...");
				RunLog.logAppend("--------------");
				
				SecondUI secondUI = new SecondUI();
            	secondUI.setVisible(true);
            	RunMain.addWindowClosingEvent(secondUI);
				
				dispose();
			}
		});
		
		RunLog.logAppend("FirstUI用户界面创建完成");
		RunLog.logAppend("--------------");
	}
}
