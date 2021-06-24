package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import top.xujiayao.gifsigngen.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * @author Xujiayao
 */
public class Avatar {

	private static boolean success;

	private Avatar() {
		throw new IllegalStateException("工具类");
	}

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
		HttpGet request = new HttpGet(Variables.projectFlyData.loginData[2].replace("\\/", "/"));
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		try {
			Variables.response = Variables.httpClient.execute(request);

			if (Variables.response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = Variables.response.getEntity();

				return ImageIO.read(new ByteArrayInputStream(EntityUtils.toByteArray(httpEntity)));
			} else {
				Platform.runLater(() -> Dialogs.showExceptionDialog(new CustomException("服务器返回状态码："
					  + Variables.response.getStatusLine().getStatusCode())));
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				if (e.getMessage().contains("Connection timed out")) {
					Dialogs.showErrorDialog("发生错误", "无法下载您的头像，请尝试重新上传一遍您的头像。如果您是中国大陆用户，使用 VPN 再试一次。");
				} else {
					Dialogs.showExceptionDialog(e);
				}
			});
		}

		return null;
	}

	public static boolean isSuccess() {
		return success;
	}

	public static void setSuccess(boolean success) {
		Avatar.success = success;
	}
}
