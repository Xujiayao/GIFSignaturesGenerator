package top.xujiayao.gifSignaturesGenerator.tools;

import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Xujiayao
 */
public class ImageUpload {

	public static String upload() throws Exception {
		OutputStream out = null;
		DataInputStream in = null;
		BufferedReader reader = null;

		String message = null;

		try {
			URLConnection conn = new URL("https://sm.ms/api/v2/upload").openConnection();

			conn.setDoOutput(true);

			conn.addRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
			conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=========7d4a6d158c9");

			out = new DataOutputStream(conn.getOutputStream());

			out.write(("--========7d4a6d158c9\nContent-Disposition: form-data; name=\"smfile\"; filename=\"signature.gif\"\nContent-Type: image/gif\n\n").getBytes());

			in = new DataInputStream(new FileInputStream(Variables.dataFolder + "/signature.gif"));
			byte[] bufferOut = new byte[1024];
			int bytes;

			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}

			out.write("\n\n--========7d4a6d158c9--\n".getBytes());

			in.close();

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

			if (in != null) {
				in.close();
			}

			if (reader != null) {
				reader.close();
			}
		}

		return message;
	}
}
