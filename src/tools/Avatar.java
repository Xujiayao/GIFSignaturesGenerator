package tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import ui.Dialogs;

/**
 * @author Xujiayao
 */
public class Avatar {
	
	public static void processAvatar() {
		try {
			BufferedImage image = ImageIO.read(new File(Variables.avatarFilePath));
			int w = image.getWidth();
			int h = image.getHeight();
			BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = output.createGraphics();
			g2.setColor(new Color(0, 0, 0, 0));
			g2.fillRect(0, 0, w, h);
			
			g2.setComposite(AlphaComposite.SrcOut);
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(0, 0, 0));
			g2.setBackground(Color.black);
			g2.setPaint(new Color(0, 0, 0));
			g2.fill(new RoundRectangle2D.Float(0, 0, w, h, 120, 120));
			
			g2.setComposite(AlphaComposite.SrcAtop);
			g2.drawImage(image, 0, 0, null);

			g2.dispose();
			
			ImageIO.write(output, "png", new File(Variables.avatarFilePath));
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
	}
	
	public static boolean downloadAvatar() {
		InputStream is = null;
		BufferedOutputStream bos = null;
		
		try {
			byte[] buff = new byte[8192];
			
			URL url = new URL(Variables.loginData[2].replace("\\/", "/"));
			
			URLConnection conn = url.openConnection();
			
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");
			
			is = conn.getInputStream();
			
			Variables.avatarFilePath = Variables.dataFolder + "/avatar" + Variables.loginData[2].substring(Variables.loginData[2].lastIndexOf("."));
			
            File file = new File(Variables.avatarFilePath);
            
            bos = new BufferedOutputStream(new FileOutputStream(file));
            
            int count = 0;
            
            while ( (count = is.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
            
            if (is != null) {
        		is.close();
        	}
        	
			if (is != null) {
				bos.close();
        	}
			
            return true;
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("Connection timed out")) {
					if (Variables.language.equals("English")) {
						Dialogs.showErrorDialog("Unable to download your avatar, please try re-uploading your avatar in projectFLY again. If you are in Mainland China, try again with a VPN.", true);
					} else {
						Dialogs.showErrorDialog("无法下载您的头像，请尝试在 projectFLY 里重新上传一遍您的头像。如果您是中国大陆用户，使用 VPN 再试一次。", false);
					}
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		} finally {
			try {
				if (is != null) {
	        		is.close();
	        	}
	        	
	        	if (is != null) {
	        		bos.close();
	        	}
			} catch (Exception e) {
				Platform.runLater(() -> {
					Dialogs.showExceptionDialog(e);
				});
			}
        }
		
		return false;
	}
}
