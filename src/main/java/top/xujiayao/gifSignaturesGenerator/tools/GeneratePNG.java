package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import org.apache.commons.io.FileUtils;
import top.xujiayao.gifSignaturesGenerator.Main;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class GeneratePNG {

	public static boolean generateProjectFly1() {
		try {
			File tempFolder = new File(Variables.dataFolder.toString() + "/temp");

			if (!tempFolder.exists() || !tempFolder.isDirectory()) {
				if (!tempFolder.mkdir()) {
					throw new Exception("自定义：无法创建文件夹");
				}
			}

			FileUtils.cleanDirectory(Variables.dataFolder + "/temp");

			for (int i = 1; i <= 5; i++) {
				BufferedImage image = new BufferedImage(3200, 200, BufferedImage.TYPE_INT_RGB);

				BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
				logo.getGraphics().drawImage(ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("pf-v2-logo.png"))), 0, 0, null);
				Graphics g = image.getGraphics();

				g.setClip(0, 0, 3200, 200);
				g.setColor(new Color(250, 250, 250));
				g.fillRect(0, 0, 3200, 200);

				g.drawImage(logo, 50, 50, 177, 100, null);

				g.setColor(new Color(73, 73, 73));
				g.setFont(new Font("Microsoft YaHei", Font.BOLD, 86));
				g.drawString(Main.projectFlyData.loginData[1], 277, 132);

				g.setColor(new Color(126, 126, 126));
				g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 58));

				switch (i) {
					case 1 -> g.drawString("Total Hours:", 1034, 121);
					case 3 -> g.drawString("Fav Aircraft:", 1045, 121);
					case 4 -> g.drawString("Fav Route:", 1081, 121);
					case 5 -> g.drawString("Avg Ldg Rate:", 986, 121);
				}

				g.setColor(new Color(246, 170, 50));
				g.setFont(new Font("Microsoft YaHei", Font.BOLD, 64));

				switch (i) {
					case 1 -> g.drawString(Main.projectFlyData.profileData[3], 1412, 124);
					case 2 -> g.drawString(Main.projectFlyData.profileData[0], 981, 124);
					case 3 -> g.drawString(Main.projectFlyData.profileData[5] + " / " + Main.projectFlyData.profileData[4], 1412, 124);
					case 4 -> g.drawString(Main.projectFlyData.logbookData[1], 1412, 124);
					case 5 -> g.drawString(Main.projectFlyData.profileData[6], 1412, 124);
				}

				g.setColor(new Color(126, 126, 126));
				g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 58));

				switch (i) {
					case 1 -> g.drawString("Total Flights:", 2266, 121);
					case 3 -> g.drawString("Fav Airport:", 2299, 121);
					case 4 -> g.drawString("Visited Countries:", 2133, 121);
					case 5 -> g.drawString("Achvs Completed:", 2118, 121);
				}

				g.setColor(new Color(246, 170, 50));
				g.setFont(new Font("Microsoft YaHei", Font.BOLD, 64));

				switch (i) {
					case 1 -> g.drawString(Main.projectFlyData.profileData[2], 2659, 124);
					case 3 -> g.drawString(Main.projectFlyData.logbookData[0], 2659, 124);
					case 4 -> g.drawString(Main.projectFlyData.passportData, 2659, 124);
					case 5 -> g.drawString(Main.projectFlyData.profileData[1], 2659, 124);
				}

				g.dispose();
				ImageIO.write(image, "png", new File(Variables.dataFolder + "/temp/temp" + i + ".png"));

				System.gc();
			}

			return true;
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return false;
	}
}
