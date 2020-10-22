package tools;

import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

import javafx.application.Platform;
import net.sf.image4j.codec.ico.ICODecoder;
import ui.Dialogs;

/**
 * @author Xujiayao
 */
public class SystemTray {

	public static java.awt.SystemTray tray;
	public static TrayIcon trayIcon;

	public static void displayTray() {
		try {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				Platform.runLater(() -> {
					Dialogs.showExceptionDialog(e);
				});
			}
			
			tray = java.awt.SystemTray.getSystemTray();

			List<BufferedImage> image = null;
			try {
				image = ICODecoder.read(SystemTray.class.getResourceAsStream("/images/icon.ico"));
			} catch (Exception e) {
				Platform.runLater(() -> {
					Dialogs.showExceptionDialog(e);
				});
			}

			JPopupMenu menu = new JPopupMenu();

			JMenuItem item1 = new JMenuItem("首选项");
			menu.add(item1);
			
			JMenuItem item2 = new JMenuItem("检查更新");
			menu.add(item2);
			
			JMenuItem item3 = new JMenuItem("关于");
			menu.add(item3);

			JMenuItem item4 = new JMenuItem("退出程序");
			menu.add(item4);

			trayIcon = new TrayIcon(image.get(image.size() - 1), "PF签名图生成工具");
			tray.add(trayIcon);

			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == 3) {
						menu.setLocation(e.getX(), e.getY() - 95);
						menu.setInvoker(menu);
						menu.setVisible(true);
					} else {
						menu.setVisible(false);
					}
				}
			});

			item1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Platform.runLater(() -> {
						Dialogs.showPreferencesDialog();
					});
				}
			});

			item2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Updates.start(false);
				}
			});

			item3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Platform.runLater(() -> {
						Dialogs.showAboutDialog();
					});
				}
			});

			item4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			if (Variables.language.equals("English")) {
				trayIcon.displayMessage("PF Signatures Generator", "PF Signatures Generator is running", TrayIcon.MessageType.NONE);
			} else {
				trayIcon.displayMessage("PF签名图生成工具", "PF签名图生成工具正在运行", TrayIcon.MessageType.NONE);
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
	}
}
