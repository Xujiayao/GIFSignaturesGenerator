package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import log.RunLog;
import main.RunMain;
import tools.GenerateGIF;
import tools.GeneratePNG;
import tools.GetScreenSize;
import tools.GetVariables;
import tools.ImageUpload;
import tools.ParseMessage;
import tools.ShowDialogs;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ScrollPaneConstants;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package ui
 * @filename FourthUI.java
 */
@SuppressWarnings("serial")
public class FourthUI extends JFrame {

	private JPanel contentPane;
	
	private static JProgressBar progressBar1;
	private static JProgressBar progressBar2;
	
	private static JLabel progressLabel1;
	private static JLabel progressLabel2;
	
	public static JTextArea textArea;
	
	/**
	 * @method main
	 * @usage 启动此用户界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FourthUI frame = new FourthUI();
					frame.setVisible(true);
				} catch (Exception e) {
					RunLog.logAppend("出现异常：" + e.getMessage());
					ShowDialogs.showErrorDialog(e);
				}
			}
		});
	}

	public FourthUI() {
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
		
		JLabel label1 = new JLabel("Generate 生成");
		label1.setBounds(211, 10, 573, 28);
		label1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		contentPane.add(label1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(211, 48, 573, 1);
		contentPane.add(separator1);
		
		JLabel label2 = new JLabel("The signature is being generated. Please wait for a few seconds.");
		label2.setBounds(211, 59, 573, 22);
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("签名图正在生成。请您稍等几秒。");
		label3.setBounds(211, 91, 573, 22);
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(label3);
		
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
		leftLabel4.setFont(new Font("微软雅黑", Font.BOLD, 16));
		leftLabel4.setBounds(10, 106, 180, 22);
		panel.add(leftLabel4);
		
		JLabel leftLabel5 = new JLabel("5. Finished");
		leftLabel5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		leftLabel5.setBounds(10, 138, 180, 22);
		panel.add(leftLabel5);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(211, 521, 573, 1);
		contentPane.add(separator2);
		
		progressBar2 = new JProgressBar();
		progressBar2.setBounds(211, 532, 573, 29);
		progressBar2.setForeground(new Color(6, 148, 50));
		progressBar2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(progressBar2);
		
		progressLabel2 = new JLabel("Overall progress: ");
		progressLabel2.setBounds(211, 489, 573, 22);
		progressLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(progressLabel2);
		
		progressLabel1 = new JLabel("Current progress: ");
		progressLabel1.setBounds(211, 134, 573, 22);
		progressLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(progressLabel1);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(211, 123, 573, 1);
		contentPane.add(separator3);
		
		progressBar1 = new JProgressBar();
		progressBar1.setBounds(211, 166, 573, 29);
		progressBar1.setForeground(new Color(6, 148, 50));
		progressBar1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(progressBar1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(211, 205, 573, 274);
		contentPane.add(scrollPane);
		
		try {
			File pngFolder = new File(GetVariables.folderFile + File.separatorChar + "png");
			
			if (!pngFolder.exists() || !pngFolder.isDirectory()) {
				pngFolder.mkdir();
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		
		RunLog.logAppend("FourthUI用户界面创建完成");
		RunLog.logAppend("--------------");
	}

	/**
	 * @method startGeneration
	 * @usage 开始生成PNG
	 */
	public void startPNGGeneration() {
		try {
			File pngFolder = new File(GetVariables.folderFile + File.separatorChar + "png");
			
			if (!pngFolder.exists() || !pngFolder.isDirectory()) {
				pngFolder.mkdir();
				RunLog.logAppend("pngFolder创建完成");
				RunLog.logAppend("--------------");
			}
			
			progressBar2.setValue(0);
			
			textArea.append("Start generating a1.png...\r\n");
			progressBar1.setValue(0);
			createTimers(100, 15, 1);
			GeneratePNG.drawImage1(GetVariables.displayName, GetVariables.totalHours, GetVariables.totalFlights);
			
			Thread.sleep(1000);
			
			textArea.append("Start generating a2.png...\r\n");
			progressBar1.setValue(0);
			createTimers(100, 29, 1);
			GeneratePNG.drawImage2(GetVariables.displayName, GetVariables.biography);
			
			Thread.sleep(1000);
			
			textArea.append("Start generating a3.png...\r\n");
			progressBar1.setValue(0);
			createTimers(100, 43, 1);
			GeneratePNG.drawImage3(GetVariables.displayName, GetVariables.favAircraft, GetVariables.favAirport);

			Thread.sleep(1000);
			
			textArea.append("Start generating a4.png...\r\n");
			progressBar1.setValue(0);
			createTimers(100, 58, 1);
			GeneratePNG.drawImage4(GetVariables.displayName, GetVariables.favRoute, GetVariables.visitedCountries);
			
			Thread.sleep(1000);
			
			textArea.append("Start generating a5.png...\r\n");
			progressBar1.setValue(0);
			createTimers(100, 72, 1);
			GeneratePNG.drawImage5(GetVariables.displayName, GetVariables.avgLdgRate);
			
			Thread.sleep(1000);
			
			textArea.append("Start generating signature.gif...\r\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			progressBar1.setValue(0);
			createTimers(100, 86, 8);
			GenerateGIF.generateGIF();

			Thread.sleep(1000);
			
			textArea.append("Start uploading signature.gif to the image hosting (https://sm.ms) via its API...\r\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			progressBar1.setValue(0);
			createTimers(80, 92, 36);
			GetVariables.returnedMessage = ImageUpload.upload(new File(GetVariables.folderFile + File.separatorChar + "signature.gif"));
			createTimers(100, 96, 1);
			
			Thread.sleep(1000);
			
			textArea.append("Parsing the message returned from the server...\r\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			progressBar1.setValue(0);
			createTimers(100, 100, 1);
			int response = ParseMessage.parse();
			
			if (response == 0) {
				Thread.sleep(2000);
				
				RunLog.logAppend("正在从FourthUI跳转至FifthUI...");
				RunLog.logAppend("--------------");
				
				FifthUI fifthUI = new FifthUI();
				fifthUI.setVisible(true);
				RunMain.addWindowClosingEvent(fifthUI);
				
				dispose();
			} else {
				RunLog.logAppend("正在从FourthUI跳转至ThirdUI...");
				RunLog.logAppend("--------------");
				
				ThirdUI thirdUI = new ThirdUI();
				thirdUI.setVisible(true);
				RunMain.addWindowClosingEvent(thirdUI);
				
				dispose();
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
			
			RunLog.logAppend("正在从FourthUI跳转至ThirdUI...");
			RunLog.logAppend("--------------");
			
			ThirdUI thirdUI = new ThirdUI();
			thirdUI.setVisible(true);
			RunMain.addWindowClosingEvent(thirdUI);
			
			dispose();
		}
	}
	
	private static void createTimers(int destValue1, int destValue2, int ms) {
		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			@Override
			public void run(){
				int value;
        		
				value = progressBar1.getValue() + 1;
				
				progressBar1.setValue(value);
				progressLabel1.setText("Current progress: " + value + "%");
				
				if (value == destValue1) {
					if (destValue1 == 100) {
						textArea.append("Completed\r\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					}
					this.cancel();
				}
			}
		}, 0, ms);
		
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			@Override
			public void run(){
				int value;
        			
				value = progressBar2.getValue() + 1;
				
				progressBar2.setValue(value);
				progressLabel2.setText("Overall progress: " + value + "%");

				if (value == destValue2) {
					this.cancel();
				}
			}
		}, 0, (8 * ms));
	}
}
