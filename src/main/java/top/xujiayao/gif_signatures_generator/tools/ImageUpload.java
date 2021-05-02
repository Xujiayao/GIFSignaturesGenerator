package top.xujiayao.gif_signatures_generator.tools;

import javafx.application.Platform;
import top.xujiayao.gif_signatures_generator.ui.Dialogs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Xujiayao
 */
public class ImageUpload {

	private ImageUpload() {
		throw new IllegalStateException("工具类");
	}

	public static String upload() throws IOException {
		OutputStream out = null;
		BufferedReader reader = null;

		String message = null;

		try (DataInputStream in = new DataInputStream(new FileInputStream(Variables.dataFolder + "/signature.gif"))) {
			URLConnection conn = new URL("https://sm.ms/api/v2/upload").openConnection();
			conn.setDoOutput(true);
			conn.addRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
			conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=========7d4a6d158c9");

			out = new DataOutputStream(conn.getOutputStream());

			out.write(("--========7d4a6d158c9\nContent-Disposition: form-data; name=\"smfile\"; filename=\"signature.gif\"\nContent-Type: image/gif\n\n").getBytes());

			byte[] bufferOut = new byte[1024];
			int bytes;

			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}

			out.write("\n\n--========7d4a6d158c9--\n".getBytes());

			out.flush();
			out.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			message = reader.readLine();

			reader.close();
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		} finally {
			if (out != null) {
				out.close();
			}

			if (reader != null) {
				reader.close();
			}
		}

		return message;
	}
}
