package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import log.RunLog;

public class GeneratePNG {
	
	public static void drawImage5(String displayName, String avgLdgRate) {
		try {
			BufferedImage bufferedImage = new BufferedImage(3200, 212, BufferedImage.TYPE_INT_RGB);
			
			BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
	        logo.getGraphics().drawImage(ImageIO.read(GeneratePNG.class.getResource("/images/logo.png")), 0, 0, null);
			Graphics g = bufferedImage.getGraphics();
			
			g.setClip(0, 0, 3200, 212);
			g.setColor(new Color(250, 250, 250));
			g.fillRect(0, 0, 3200, 212);
			
			g.drawImage(logo, 33, 40, 177, 100, null);
			
			g.setColor(new Color(73, 73, 73));
			g.setFont(new Font("微软雅黑", Font.BOLD, 86));
            g.drawString(displayName, 250, 132);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Avg Ldg Rate:", 970, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(avgLdgRate, 1400, 127);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("@Xujiayao/3411", 2335, 130);
            
			g.dispose();
			ImageIO.write(bufferedImage, "png", new File(GetVariables.folderFile + File.separatorChar + "png" + File.separatorChar + "a5.png"));
			
			RunLog.logAppend("a5.png生成完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		}
	}

	public static void drawImage4(String displayName, String favRoute, String visitedCountries) {
		try {
			BufferedImage bufferedImage = new BufferedImage(3200, 212, BufferedImage.TYPE_INT_RGB);
			
			BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
	        logo.getGraphics().drawImage(ImageIO.read(GeneratePNG.class.getResource("/images/logo.png")), 0, 0, null);
			Graphics g = bufferedImage.getGraphics();
			
			g.setClip(0, 0, 3200, 212);
			g.setColor(new Color(250, 250, 250));
			g.fillRect(0, 0, 3200, 212);
			
			g.drawImage(logo, 33, 40, 177, 100, null);
			
			g.setColor(new Color(73, 73, 73));
			g.setFont(new Font("微软雅黑", Font.BOLD, 86));
            g.drawString(displayName, 250, 132);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Fav Route:", 1065, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(favRoute, 1400, 127);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Visited Countries:", 2169, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(visitedCountries, 2700, 127);
            
			g.dispose();
			ImageIO.write(bufferedImage, "png", new File(GetVariables.folderFile + File.separatorChar + "png" + File.separatorChar + "a4.png"));
			
			RunLog.logAppend("a4.png生成完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		}
	}

	public static void drawImage3(String displayName, String favAircraft, String favAirport) {
		try {
			BufferedImage bufferedImage = new BufferedImage(3200, 212, BufferedImage.TYPE_INT_RGB);
			
			BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
	        logo.getGraphics().drawImage(ImageIO.read(GeneratePNG.class.getResource("/images/logo.png")), 0, 0, null);
			Graphics g = bufferedImage.getGraphics();
			
			g.setClip(0, 0, 3200, 212);
			g.setColor(new Color(250, 250, 250));
			g.fillRect(0, 0, 3200, 212);
			
			g.drawImage(logo, 33, 40, 177, 100, null);
			
			g.setColor(new Color(73, 73, 73));
			g.setFont(new Font("微软雅黑", Font.BOLD, 86));
            g.drawString(displayName, 250, 132);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Fav Aircraft:", 1028, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(favAircraft, 1400, 127);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Fav Airport:", 2335, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(favAirport, 2700, 127);
            
			g.dispose();
			ImageIO.write(bufferedImage, "png", new File(GetVariables.folderFile + File.separatorChar + "png" + File.separatorChar + "a3.png"));
			
			RunLog.logAppend("a3.png生成完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		}
	}

	public static void drawImage2(String displayName, String biography) {
		try {
			BufferedImage bufferedImage = new BufferedImage(3200, 212, BufferedImage.TYPE_INT_RGB);
			
			BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
	        logo.getGraphics().drawImage(ImageIO.read(GeneratePNG.class.getResource("/images/logo.png")), 0, 0, null);
			Graphics g = bufferedImage.getGraphics();
			
			g.setClip(0, 0, 3200, 212);
			g.setColor(new Color(250, 250, 250));
			g.fillRect(0, 0, 3200, 212);
			
			g.drawImage(logo, 33, 40, 177, 100, null);
			
			g.setColor(new Color(73, 73, 73));
			g.setFont(new Font("微软雅黑", Font.BOLD, 86));
            g.drawString(displayName, 250, 132);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(biography, 970, 127);
            
			g.dispose();
			ImageIO.write(bufferedImage, "png", new File(GetVariables.folderFile + File.separatorChar + "png" + File.separatorChar + "a2.png"));
			
			RunLog.logAppend("a2.png生成完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		}
	}

	public static void drawImage1(String displayName, String totalHours, String totalFlights) {
		try {
			BufferedImage bufferedImage = new BufferedImage(3200, 212, BufferedImage.TYPE_INT_RGB);
			
			BufferedImage logo = new BufferedImage(177, 100, BufferedImage.TYPE_INT_RGB);
	        logo.getGraphics().drawImage(ImageIO.read(GeneratePNG.class.getResource("/images/logo.png")), 0, 0, null);

			Graphics g = bufferedImage.getGraphics();
			
			g.setClip(0, 0, 3200, 212);
			g.setColor(new Color(250, 250, 250));
			g.fillRect(0, 0, 3200, 212);
			
			g.drawImage(logo, 33, 40, 177, 100, null);
			
			g.setColor(new Color(73, 73, 73));
			g.setFont(new Font("微软雅黑", Font.BOLD, 86));
            g.drawString(displayName, 250, 132);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Total Hours:", 1018, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(totalHours, 1400, 127);
            
            g.setColor(new Color(126, 126, 126));
            g.setFont(new Font("微软雅黑", Font.PLAIN, 58));
            g.drawString("Total Flights:", 2302, 130);
            
            g.setColor(new Color(246, 170, 50));
            g.setFont(new Font("微软雅黑", Font.BOLD, 64));
            g.drawString(totalFlights, 2700, 127);
            
			g.dispose();
			ImageIO.write(bufferedImage, "png", new File(GetVariables.folderFile + File.separatorChar + "png" + File.separatorChar + "a1.png"));
			
			RunLog.logAppend("a1.png生成完成");
			RunLog.logAppend("--------------");
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);		}
	}
}
