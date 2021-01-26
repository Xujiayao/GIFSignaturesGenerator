package io.gitee.xujiayao147.gifSignaturesGenerator.ui;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Xujiayao
 */
public class SystemTray implements Runnable {

	public TrayIcon trayIcon;

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();

			JPopupMenu menu = new JPopupMenu();

			JMenuItem item1 = new JMenuItem("首选项");
			menu.add(item1);

			JMenuItem item2 = new JMenuItem("检查更新");
			menu.add(item2);

			JMenuItem item3 = new JMenuItem("关于");
			menu.add(item3);

			JMenuItem item4 = new JMenuItem("退出");
			menu.add(item4);

			trayIcon = new TrayIcon(Main.variables.icons.get(11), "GIF签名图生成工具");

			tray.add(trayIcon);

			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getButton() == 3) {
						menu.setLocation(e.getX(), e.getY() - 110);
						menu.setInvoker(menu);
						menu.setVisible(true);
					} else {
						menu.setVisible(false);
					}
				}
			});

			item1.addActionListener(e -> Platform.runLater(() -> Main.dialogs.showPreferencesDialog()));

			item2.addActionListener(e -> {
				Main.update.isManualRequest = true;
				new Thread(Main.update).start();
			});

			item3.addActionListener(e -> Platform.runLater(() -> Main.dialogs.showAboutDialog()));

			item4.addActionListener(e -> {
				Platform.runLater(() -> Main.stage.close());
				Platform.exit();
				System.exit(0);
			});

			trayIcon.displayMessage("GIF签名图生成工具", "GIF签名图生成工具正在运行", TrayIcon.MessageType.NONE);
		} catch (Exception e) {
			Platform.runLater(() -> Main.dialogs.showExceptionDialog(e));
		}
	}
}
