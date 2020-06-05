package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import log.RunLog;
import main.RunMain;
import tools.DownloadHTML;
import tools.GetScreenSize;
import tools.GetVariables;
import tools.SaveVariablesFiles;
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

import javax.swing.JTextField;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package ui
 * @filename WelcomeUI.java
 */
@SuppressWarnings("serial")
public class SecondUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	
	/**
	 * @method main
	 * @usage 启动此用户界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondUI frame = new SecondUI();
					frame.setVisible(true);
				} catch (Exception e) {
					RunLog.logAppend("出现异常：" + e.getMessage());
					ShowDialogs.showErrorDialog(e);
				}
			}
		});
	}
	
	public SecondUI() {
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
		
		JLabel label1 = new JLabel("Download \u4E0B\u8F7D");
		label1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label1.setBounds(211, 10, 573, 28);
		contentPane.add(label1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(211, 48, 573, 1);
		contentPane.add(separator1);
		
		JLabel label2 = new JLabel("Please enter your projectFLY username in the textField below.");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label2.setBounds(211, 59, 573, 22);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("\u8BF7\u5728\u4E0B\u65B9\u7684\u6587\u672C\u6846\u5185\u8F93\u5165\u60A8\u7684projectFLY\u7528\u6237\u540D\u3002");
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label3.setBounds(211, 91, 573, 22);
		contentPane.add(label3);
		
		JButton button = new JButton("Cancel");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(211, 533, 89, 28);
		contentPane.add(button);
		
		JButton button2 = new JButton("Next ->");
		button2.setEnabled(false);
		button2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button2.setBounds(691, 533, 93, 28);
		contentPane.add(button2);
		
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
		leftLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JLabel leftLabel2 = new JLabel("2. Download");
		leftLabel2.setFont(new Font("微软雅黑", Font.BOLD, 16));
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
		
		JButton button1 = new JButton("<- Back");
		button1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button1.setBounds(588, 533, 93, 28);
		contentPane.add(button1);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField.setBounds(211, 123, 573, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label5 = new JLabel("<html>\u70B9\u51FBDownload\u6309\u94AE\u4EE5\u4E0B\u8F7DHTML\u6587\u4EF6\u3002<br>\u8BF7\u7B49\u5F85\u51E0\u79D2\u76F4\u81F3\u4E0B\u8F7D\u5B8C\u6210\u3002<br>\u4E0B\u8F7D\u6210\u529F\u5B8C\u6210\u65F6\uFF0CNext\u6309\u94AE\u5C06\u4F1A\u88AB\u542F\u7528\u3002</html>");
		label5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label5.setBounds(211, 269, 573, 66);
		contentPane.add(label5);
		
		JLabel label4 = new JLabel("<html>Press the Download button to download the HTML file.<br>Please wait for a few seconds until download completes.<br>The Next button will be enabled when download completes successfully.</html>");
		label4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label4.setBounds(211, 193, 573, 66);
		contentPane.add(label4);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnDownload.setBounds(211, 345, 573, 28);
		contentPane.add(btnDownload);
		
		JLabel label6 = new JLabel("");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label6.setBounds(211, 383, 573, 22);
		contentPane.add(label6);
		
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
		
		button1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					RunLog.logAppend("正在从SecondUI跳转至FirstUI...");
					RunLog.logAppend("--------------");
					
					FirstUI firstUI = new FirstUI();
					firstUI.setVisible(true);
					RunMain.addWindowClosingEvent(firstUI);
					
					dispose();
				}
			}
		});
		
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				RunLog.logAppend("正在从SecondUI跳转至FirstUI...");
				RunLog.logAppend("--------------");
				
				FirstUI firstUI = new FirstUI();
				firstUI.setVisible(true);
				RunMain.addWindowClosingEvent(firstUI);
				
				dispose();
			}
		});
		
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				SaveVariablesFiles.saveVariable("username.txt", textField.getText());
				
				if (button2.isEnabled()) {
					ShowDialogs.
					showMessageDialog("Detected Next button is enabled, to prevent any compatibility problem, we will go back<br>"
							+
							"to the previous page, and then please press the Download button again.<br><br>"
							+ "检测到Next按钮已被启用，为避免任何兼容性问题，我们将返回到上一页，请重新点选Download按钮。");
					
					RunLog.logAppend("正在从SecondUI跳转至FirstUI...");
					RunLog.logAppend("--------------");
					
					FirstUI firstUI = new FirstUI(); firstUI.setVisible(true);
					RunMain.addWindowClosingEvent(firstUI);
					
					dispose();
				}
								
				RunLog.logAppend("username已保存");
				RunLog.logAppend("开始下载HTML文件，账号：" + GetVariables.username);				
				
				int ifDownloadSuccess = DownloadHTML.downloadHTML(textField.getText());

				if (ifDownloadSuccess == 0) {
					button2.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent arg0) {
							if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
								RunLog.logAppend("正在从SecondUI跳转至ThirdUI...");
								RunLog.logAppend("--------------");
								
								ThirdUI thirdUI = new ThirdUI();
								thirdUI.setVisible(true);
								RunMain.addWindowClosingEvent(thirdUI);
								
								dispose();
							}
						}
					});
					
					button2.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent arg0) {
							RunLog.logAppend("正在从SecondUI跳转至ThirdUI...");
							RunLog.logAppend("--------------");
							
							ThirdUI thirdUI = new ThirdUI();
							thirdUI.setVisible(true);
							RunMain.addWindowClosingEvent(thirdUI);
							
							dispose();
						}
					});
					
					button2.setEnabled(true);
					label6.setForeground(new Color(0, 153, 51));
					label6.setText("Download completed");
				} else {
					label6.setForeground(new Color(255, 0, 0));
					label6.setText("Download failed");
				}
			}
		});
		
		btnDownload.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				SaveVariablesFiles.saveVariable("username.txt", textField.getText());
				
				if (button2.isEnabled()) {
					
					ShowDialogs.
					showMessageDialog("Detected Next button is enabled, to prevent any compatibility problem, we will go back<br>"
							+
							"to the previous page, and then please press the Download button again.<br><br>"
							+ "检测到Next按钮已被启用，为避免任何兼容性问题，我们将返回到上一页，请重新点选Download按钮。");
					
					RunLog.logAppend("正在从SecondUI跳转至FirstUI...");
					RunLog.logAppend("--------------");
					
					FirstUI firstUI = new FirstUI(); firstUI.setVisible(true);
					RunMain.addWindowClosingEvent(firstUI);
					  
					dispose();
				}
				
				RunLog.logAppend("username已保存");
				RunLog.logAppend("开始下载HTML文件，账号：" + GetVariables.username);				
				
				int ifDownloadSuccess = DownloadHTML.downloadHTML(textField.getText());
				
				if (ifDownloadSuccess == 0) {
					button2.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent arg0) {
							if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
								RunLog.logAppend("正在从SecondUI跳转至ThirdUI...");
								RunLog.logAppend("--------------");
								
								ThirdUI thirdUI = new ThirdUI();
								thirdUI.setVisible(true);
								RunMain.addWindowClosingEvent(thirdUI);
								
								dispose();
							}
						}
					});
					
					button2.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent arg0) {
							RunLog.logAppend("正在从SecondUI跳转至ThirdUI...");
							RunLog.logAppend("--------------");
							
							ThirdUI thirdUI = new ThirdUI();
							thirdUI.setVisible(true);
							RunMain.addWindowClosingEvent(thirdUI);
							
							dispose();
						}
					});
					
					button2.setEnabled(true);
					label6.setForeground(new Color(0, 153, 51));
					label6.setText("Download completed");
				} else {
					label6.setForeground(new Color(255, 0, 0));
					label6.setText("Download failed");
				}
			}
		});
		
		GetVariables.runGetVariables();
		textField.setText(GetVariables.username);
		
		RunLog.logAppend("默认username加载完成");
		
		RunLog.logAppend("SecondUI用户界面创建完成");
		RunLog.logAppend("--------------");
	}
}
