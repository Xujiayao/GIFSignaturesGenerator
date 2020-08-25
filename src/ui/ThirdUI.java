package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import log.RunLog;
import main.RunMain;
import tools.GetScreenSize;
import tools.GetVariables;
import tools.ParseFile;
import tools.ShowDialogs;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package ui
 * @filename ThirdUI.java
 */
@SuppressWarnings("serial")
public class ThirdUI extends JFrame {

	private JPanel contentPane;
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	private JTextField textField8;
	private JTextField textField9;

	/**
	 * @method main
	 * @usage 启动此用户界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThirdUI frame = new ThirdUI();
					frame.setVisible(true);
				} catch (Exception e) {
					RunLog.logAppend("出现异常：" + e.getMessage());
					ShowDialogs.showErrorDialog(e);
				}
			}
		});
	}
	
	public ThirdUI() {
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
		
		JLabel label1 = new JLabel("Parse \u89E3\u6790");
		label1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label1.setBounds(211, 10, 573, 28);
		contentPane.add(label1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(211, 48, 573, 1);
		contentPane.add(separator1);
		
		JLabel label2 = new JLabel("<html>Please check your parse results. Biography is the only editable variables.<br><font color=\"#FF0000\">Please note that the text should not overflow the textField excessively.</font></html>");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label2.setBounds(211, 59, 573, 44);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("<html>请检查您的解析结果。个人简介是唯一允许编辑的变量。<br><font color=\"#FF0000\">请注意，文本不应过度溢出文本框。</font></html>");
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label3.setBounds(211, 113, 573, 44);
		contentPane.add(label3);
		
		JButton button = new JButton("Cancel");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(211, 533, 89, 28);
		contentPane.add(button);
		
		JButton button2 = new JButton("Next ->");
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
		leftLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel2.setBounds(10, 42, 180, 22);
		panel.add(leftLabel2);
		
		JLabel leftLabel3 = new JLabel("3. Parse");
		leftLabel3.setFont(new Font("微软雅黑", Font.BOLD, 16));
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
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField1.setBounds(371, 179, 413, 28);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel variableLabel1 = new JLabel("Display Name:");
		variableLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel1.setBounds(211, 179, 150, 28);
		contentPane.add(variableLabel1);
		
		JLabel variableLabel2 = new JLabel("Total Hours:");
		variableLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel2.setBounds(211, 217, 150, 28);
		contentPane.add(variableLabel2);
		
		JLabel variableLabel3 = new JLabel("Total Flights:");
		variableLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel3.setBounds(211, 255, 150, 28);
		contentPane.add(variableLabel3);
		
		JLabel variableLabel4 = new JLabel("Biography:");
		variableLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel4.setBounds(211, 293, 150, 28);
		contentPane.add(variableLabel4);
		
		JLabel variableLabel5 = new JLabel("Fav Aircraft:");
		variableLabel5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel5.setBounds(211, 331, 150, 28);
		contentPane.add(variableLabel5);
		
		JLabel variableLabel6 = new JLabel("Fav Airport:");
		variableLabel6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel6.setBounds(211, 369, 150, 28);
		contentPane.add(variableLabel6);
		
		JLabel variableLabel7 = new JLabel("Fav Route:");
		variableLabel7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel7.setBounds(211, 407, 150, 28);
		contentPane.add(variableLabel7);
		
		JLabel variableLabel8 = new JLabel("Visited Countries:");
		variableLabel8.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel8.setBounds(211, 445, 150, 28);
		contentPane.add(variableLabel8);
		
		JLabel variableLabel9 = new JLabel("Avg Ldg Rate:");
		variableLabel9.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel9.setBounds(211, 483, 150, 28);
		contentPane.add(variableLabel9);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField2.setColumns(10);
		textField2.setBounds(371, 217, 413, 28);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField3.setColumns(10);
		textField3.setBounds(371, 255, 413, 28);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField4.setColumns(10);
		textField4.setBounds(371, 293, 413, 28);
		contentPane.add(textField4);
		
		textField5 = new JTextField();
		textField5.setEditable(false);
		textField5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField5.setColumns(10);
		textField5.setBounds(371, 331, 413, 28);
		contentPane.add(textField5);
		
		textField6 = new JTextField();
		textField6.setEditable(false);
		textField6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField6.setColumns(10);
		textField6.setBounds(371, 369, 413, 28);
		contentPane.add(textField6);
		
		textField7 = new JTextField();
		textField7.setEditable(false);
		textField7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField7.setColumns(10);
		textField7.setBounds(371, 407, 413, 28);
		contentPane.add(textField7);
		
		textField8 = new JTextField();
		textField8.setEditable(false);
		textField8.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField8.setColumns(10);
		textField8.setBounds(371, 445, 413, 28);
		contentPane.add(textField8);
		
		textField9 = new JTextField();
		textField9.setEditable(false);
		textField9.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField9.setColumns(10);
		textField9.setBounds(371, 483, 413, 28);
		contentPane.add(textField9);
		
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
					RunLog.logAppend("正在从ThirdUI跳转至SecondUI...");
					RunLog.logAppend("--------------");
					
					SecondUI secondUI = new SecondUI();
					secondUI.setVisible(true);
					RunMain.addWindowClosingEvent(secondUI);
					
					dispose();
				}
			}
		});
		
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				RunLog.logAppend("正在从ThirdUI跳转至SecondUI...");
				RunLog.logAppend("--------------");
				
				SecondUI secondUI = new SecondUI();
				secondUI.setVisible(true);
				RunMain.addWindowClosingEvent(secondUI);
				
				dispose();
			}
		});
		
		button2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					RunLog.logAppend("正在从ThirdUI跳转至FourthUI...");
					RunLog.logAppend("--------------");
					
					GetVariables.biography = textField4.getText();
					
					FourthUI fourthUI = new FourthUI();
	            	fourthUI.setVisible(true);
	            	RunMain.addWindowClosingEvent(fourthUI);
	            	
	            	Timer timer = new Timer();
	        		timer.schedule(new TimerTask() {
	        			@Override
	        			public void run(){
	                    	fourthUI.startPNGGeneration();
	        			}
	        		}, 1000);
	            	
					dispose();
				}
			}
		});
		
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				RunLog.logAppend("正在从ThirdUI跳转至FourthUI...");
				RunLog.logAppend("--------------");
				
				GetVariables.biography = textField4.getText();

				FourthUI fourthUI = new FourthUI();
            	fourthUI.setVisible(true);
            	RunMain.addWindowClosingEvent(fourthUI);
            	
            	Timer timer = new Timer();
        		timer.schedule(new TimerTask() {
        			@Override
        			public void run(){
                    	fourthUI.startPNGGeneration();
        			}
        		}, 1000);
            	
            	dispose();
			}
		});
		
		parse();
		
		RunLog.logAppend("ThirdUI用户界面创建完成");
		RunLog.logAppend("--------------");
	}

	/**
	 * @method parse
	 * @usage 调用解析方法
	 */
	private void parse() {
		try {
			String string1 = ParseFile.parseFile("", 0, 1, 5, 139);
			textField1.setText(string1);
			GetVariables.displayName = string1;
			
			String string2 = ParseFile.parseFile("Total Hours", -1, 2, 7, -1);
			textField2.setText(string2);
			GetVariables.totalHours = string2;

			String string3 = ParseFile.parseFile("Total Flights", -1, 3, 7, -1);
			textField3.setText(string3);
			GetVariables.totalFlights = string3;

			String string4 = ParseFile.parseBiography();
			textField4.setText(string4);
			
			String string5 = ParseFile.parseFile("Most Flown Aircraft", -1, 5, 7, -1);
			textField5.setText(string5);
			GetVariables.favAircraft = string5;

			String string6 = ParseFile.parseFile("Most Visited Airfield", -1, 6, 7, -1);
			textField6.setText(string6);
			GetVariables.favAirport = string6;

			String string7 = ParseFile.parseFile("Most Flown Route", -1, 7, 7, -1);
			textField7.setText(string7);
			GetVariables.favRoute = string7;

			String string8 = ParseFile.parseFile("Visited Countries", 0, 4, 46, -1);
			textField8.setText(string8);
			GetVariables.visitedCountries = string8;

			String string9 = ParseFile.parseFile("Avg Ldg Rate", 0, 9, 41, -1);
			textField9.setText(string9);
			GetVariables.avgLdgRate = string9;

			RunLog.logAppend("解析完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("解析失败，出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
			RunLog.logAppend("--------------");
		}
	}
}
