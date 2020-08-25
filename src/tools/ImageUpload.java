package tools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename ImageUpload.java
 */
public class ImageUpload {
	
	/**
	 * @method upload
	 * @usage 上传图片到sm.ms图床
	 */
	public static String upload(File file) {
		OutputStream out;
		DataInputStream in;
		BufferedReader reader;
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			String fileName = file.getName();
			
			final String newLine = "\r\n";
			final String boundaryPrefix = "--";
			
			String BOUNDARY = "========7d4a6d158c9";
			
			URL url = new URL("https://sm.ms/api/v2/upload");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("User-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			out = new DataOutputStream(conn.getOutputStream());
			
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);
			
			sb.append("Content-Disposition: form-data;name=\"smfile\";filename=\"" + fileName + "\"" + newLine);
			sb.append("Content-Type:image/gif");
			
			sb.append(newLine);
			sb.append(newLine);
			
			out.write(sb.toString().getBytes());

			in = new DataInputStream(new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			
			out.write(newLine.getBytes());
			
			in.close();
			
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
			
			out.write(end_data);
			
			out.flush();
			out.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			
			reader.close();
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return buffer.toString();
	}
}
