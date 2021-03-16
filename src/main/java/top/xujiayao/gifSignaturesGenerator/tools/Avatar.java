package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.Main;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Avatar {

	public static boolean success;

	public static BufferedImage processAvatar(BufferedImage avatar) {
		try {
			int w = avatar.getWidth();
			int h = avatar.getHeight();

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

			g2.drawImage(avatar, 0, 0, null);
			g2.dispose();

			return output;
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return null;
	}

	public static BufferedImage downloadAvatar() {
		InputStream is = null;
		ByteArrayOutputStream output = null;

		try {
			byte[] buff = new byte[8192];

			URL url = new URL(Main.projectFlyData.loginData[2].replace("\\/", "/"));

			URLConnection conn = url.openConnection();

			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

			is = conn.getInputStream();

			output = new ByteArrayOutputStream();

			int count;

			while ((count = is.read(buff)) != -1) {
				output.write(buff, 0, count);
			}

			is.close();
			output.close();

			return ImageIO.read(new ByteArrayInputStream(output.toByteArray()));
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("Connection timed out")) {
					Dialogs.showErrorDialog("发生错误", "无法下载您的头像，请尝试重新上传一遍您的头像。如果您是中国大陆用户，使用 VPN 再试一次。");
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		} finally {
			try {
				if (is != null) {
					is.close();
				}

				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				Platform.runLater(() -> Dialogs.showExceptionDialog(e));
			}
		}

		return null;
	}
}
