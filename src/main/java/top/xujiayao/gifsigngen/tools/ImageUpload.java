package top.xujiayao.gifsigngen.tools;

import javafx.application.Platform;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import top.xujiayao.gifsigngen.ui.Dialogs;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * @author Xujiayao
 */
public class ImageUpload {

	private ImageUpload() {
		throw new IllegalStateException("工具类");
	}

	public static String upload() {
		HttpPost request = new HttpPost("https://sm.ms/api/v2/upload");
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) projectfly/4.0.3 Chrome/83.0.4103.104 Electron/9.0.4 Safari/537.36");

		try (ByteArrayOutputStream os = new ByteArrayOutputStream();
		     DataInputStream in = new DataInputStream(new FileInputStream(Variables.dataFolder + "/signature.gif"))) {
			os.write(("--========7d4a6d158c9\nContent-Disposition: form-data; name=\"smfile\"; filename=\"signature.gif\"\nContent-Type: image/gif\n\n").getBytes());

			byte[] bufferOut = new byte[1024];
			int bytes;

			while ((bytes = in.read(bufferOut)) != -1) {
				os.write(bufferOut, 0, bytes);
			}

			os.write("\n\n--========7d4a6d158c9--\n".getBytes());

			ByteArrayEntity entity = new ByteArrayEntity(os.toByteArray());
			entity.setContentType("multipart/form-data; boundary=========7d4a6d158c9");
			request.setEntity(entity);
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return Utils.getHttpResponse(request, null);
	}
}
