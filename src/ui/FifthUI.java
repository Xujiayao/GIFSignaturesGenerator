package ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import log.RunLog;
import main.RunMain;
import tools.GetScreenSize;
import tools.GetVariables;
import tools.ShowDialogs;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

/**
 * @author 许佳耀
 *
 * @project GenerateProjectFlySignatures
 *
 * @package ui
 * @filename FifthUI.java
 */
@SuppressWarnings("serial")
public class FifthUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	
	/**
	 * @method main
	 * @usage 启动此用户界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FifthUI frame = new FifthUI();
					frame.setVisible(true);
				} catch (Exception e) {
					RunLog.logAppend("出现异常：" + e.getMessage());
					ShowDialogs.showErrorDialog(e);
				}
			}
		});
	}
	
	public FifthUI() {
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
		
		JLabel label1 = new JLabel("Finished 完成");
		label1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label1.setBounds(211, 10, 573, 28);
		contentPane.add(label1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(211, 48, 573, 1);
		contentPane.add(separator1);
		
		JButton button2 = new JButton("Exit");
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
		leftLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel3.setBounds(10, 74, 180, 22);
		panel.add(leftLabel3);
		
		JLabel leftLabel4 = new JLabel("4. Generate");
		leftLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel4.setBounds(10, 106, 180, 22);
		panel.add(leftLabel4);
		
		JLabel leftLabel5 = new JLabel("5. Finished");
		leftLabel5.setFont(new Font("微软雅黑", Font.BOLD, 16));
		leftLabel5.setBounds(10, 138, 180, 22);
		panel.add(leftLabel5);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(211, 521, 573, 1);
		contentPane.add(separator2);
		
		JButton button1 = new JButton("<- Back");
		button1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button1.setBounds(588, 533, 93, 28);
		contentPane.add(button1);
		
		JLabel label3 = new JLabel("<html>GenerateProjectFlySignatures 已经成功完成。<br>签名图已经生成，并存储在特定位置。签名图也已经成功上传至图床。</html>");
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label3.setBounds(211, 135, 573, 44);
		contentPane.add(label3);
		
		JLabel label2 = new JLabel("<html>GenerateProjectFlySignatures has been completed successfully.<br>The signature has been generated and saved in the specified location.<br>The signature has also been successfully uploaded to the image hosting.</html>");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label2.setBounds(211, 59, 573, 66);
		contentPane.add(label2);
		
		JLabel label = new JLabel("WHOLE PROGRAM contributed by Xujiayao");
		label.setForeground(new Color(0, 0, 255));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(211, 533, 367, 28);
		contentPane.add(label);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(211, 189, 573, 1);
		contentPane.add(separator3);
		
		JLabel variableLabel1 = new JLabel("Success:");
		variableLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel1.setBounds(211, 200, 100, 28);
		contentPane.add(variableLabel1);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField1.setBounds(321, 200, 100, 28);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel variableLabel2 = new JLabel("Code:");
		variableLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel2.setBounds(431, 200, 55, 28);
		contentPane.add(variableLabel2);
		
		JLabel variableLabel3 = new JLabel("Message:");
		variableLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel3.setBounds(211, 238, 100, 28);
		contentPane.add(variableLabel3);
		
		JLabel variableLabel4 = new JLabel("ImageURL:");
		variableLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableLabel4.setBounds(211, 276, 100, 28);
		contentPane.add(variableLabel4);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField2.setColumns(10);
		textField2.setBounds(496, 200, 288, 28);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField3.setColumns(10);
		textField3.setBounds(321, 238, 463, 28);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setEditable(false);
		textField4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField4.setColumns(10);
		textField4.setBounds(321, 276, 463, 28);
		contentPane.add(textField4);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(211, 314, 573, 1);
		contentPane.add(separator4);
		
		JLabel label4 = new JLabel("<html>Please click on the button below to check if it contains any problem. If there is, copy the DeleteURL and access it in your browser. (cautious)</html>");
		label4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label4.setBounds(211, 325, 573, 44);
		contentPane.add(label4);
		
		JLabel lblDeleteurl = new JLabel("<html>请点击下方的按钮以检查签名图是否含有任何错误。若有，复制 DeleteURL，使用您的浏览器访问该网页。（谨慎）</html>");
		lblDeleteurl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblDeleteurl.setBounds(211, 379, 573, 44);
		contentPane.add(lblDeleteurl);
		
		JButton variableButton1 = new JButton("Access the image in your default browser");
		variableButton1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableButton1.setBounds(211, 445, 573, 28);
		contentPane.add(variableButton1);
		
		JButton variableButton2 = new JButton("COPY ImageURL");
		variableButton2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableButton2.setForeground(new Color(0, 153, 51));
		variableButton2.setBounds(211, 483, 367, 28);
		contentPane.add(variableButton2);
		
		JButton variableButton3 = new JButton("COPY DeleteURL");
		variableButton3.setForeground(new Color(255, 0, 0));
		variableButton3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		variableButton3.setBounds(588, 483, 196, 28);
		contentPane.add(variableButton3);
		
		button2.addMouseListener(new MouseAdapter() {
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
		
		button2.addKeyListener(new KeyAdapter() {
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
					RunLog.logAppend("正在从FifthUI跳转至ThirdUI...");
					RunLog.logAppend("--------------");
					
					ThirdUI thirdUI = new ThirdUI();
					thirdUI.setVisible(true);
					RunMain.addWindowClosingEvent(thirdUI);
					
					dispose();
				}
			}
		});
		
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				RunLog.logAppend("正在从FifthUI跳转至ThirdUI...");
				RunLog.logAppend("--------------");
				
				ThirdUI thirdUI = new ThirdUI();
				thirdUI.setVisible(true);
				RunMain.addWindowClosingEvent(thirdUI);
				
				dispose();
			}
		});
		
		variableButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
		        if(java.awt.Desktop.isDesktopSupported()){
		            try{
		                URI uri = URI.create(GetVariables.imageUrl);
		                Desktop desktop = Desktop.getDesktop();
		                if(desktop.isSupported(Desktop.Action.BROWSE)){
		                    desktop.browse(uri);
		                }
		            }catch(Exception e){
		            	RunLog.logAppend("出现异常：" + e.getMessage());
						ShowDialogs.showErrorDialog(e);
		            }
		        }
			}
		});
		
		variableButton1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try{
		                URI uri = URI.create(GetVariables.imageUrl);
		                Desktop desktop = Desktop.getDesktop();
		                if(desktop.isSupported(Desktop.Action.BROWSE)){
		                    desktop.browse(uri);
		                }
		            }catch(Exception e){
		            	RunLog.logAppend("出现异常：" + e.getMessage());
						ShowDialogs.showErrorDialog(e);
		            }
				}
			}
		});
		
		variableButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable transferable = new StringSelection(GetVariables.imageUrl);  
		        clipboard.setContents(transferable, null);  
			}
		});
		
		variableButton2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable transferable = new StringSelection(GetVariables.imageUrl);  
		        clipboard.setContents(transferable, null);  
			}
		});
		
		variableButton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable transferable = new StringSelection(GetVariables.deleteUrl);  
		        clipboard.setContents(transferable, null);  
			}
		});
		
		variableButton3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable transferable = new StringSelection(GetVariables.deleteUrl);  
		        clipboard.setContents(transferable, null);  
			}
		});
		
		setTexts();
		
		RunLog.logAppend("FifthUI用户界面创建完成");
		RunLog.logAppend("--------------");
	}

	/**
	 * @method setTexts
	 * @usage 设置文本框内的文本
	 */
	private void setTexts() {
		textField1.setText(GetVariables.success);
		textField2.setText(GetVariables.code);
		textField3.setText(GetVariables.message);
		textField4.setText(GetVariables.imageUrl);
	}
}

