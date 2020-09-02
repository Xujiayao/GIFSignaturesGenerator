package tools;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.Dialogs;

/**
 * @author Xujiayao
 */
public class Updates {
	
	public static double downloadProgress = 0.0;
	
	public static void start(boolean auto) {
		String data = Updates.downloadJSON("https://cdn.jsdelivr.net/gh/Xujiayao147/PFSignaturesGenerator/update/version.json");
		
		try {
			if (data == null) {
				data = Updates.downloadJSON("https://raw.githubusercontent.com/Xujiayao147/PFSignaturesGenerator/master/update/version.json");
			}
		} catch (Exception e) {
			data = Updates.downloadJSON("https://raw.githubusercontent.com/Xujiayao147/PFSignaturesGenerator/master/update/version.json");
		}
		
		String[] datas = parseData(data);
		
		Platform.runLater(() -> {
			if (datas[0].equals(Variables.version)) {
				if (!auto) {
					if (Variables.language.equals("English")) {
						Dialogs.showMessageDialog("You are using the latest version of PF Signatures Generator.", "Check for Updates", true);
					} else {
						Dialogs.showMessageDialog("您正在使用最新版本的PF签名图生成工具。", "检查更新", false);
					}
				}
			} else {
				boolean response;
				
				if (Variables.language.equals("English")) {
					response = Dialogs.showConfirmDialog("PF Signatures Generator " + datas[0] + " is available (You are v2.0). Do you want to update now?\n\nRelease notes: " + datas[2], "Check for Updates", "An update of PF Signatures Generator is available!", true);
				} else {
					response = Dialogs.showConfirmDialog("PF签名图生成工具 " + datas[0] + " 现在可用（您是 v2.0）。您想要现在更新吗？\n\n更新说明：" + datas[1], "检查更新", "新版本PF签名图生成工具可用！", false);
				}
				
				if (response) {
					Dialogs.showDownloadingDialog();
					
					new Thread(new DownloadThread(datas[3], datas[4])).start();
				}
			}
		});
	}
	
	public static File saveFile(byte[] data) {
		try {
			FileChooser fileChooser = new FileChooser();
			
			try {
				fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			} catch (Exception e) {
				Dialogs.showExceptionDialog(e);
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
			}
			
			if (Variables.language.equals("中文")) {
				fileChooser.setTitle("保存新版本");
	            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR 文件", "*.jar"));
			} else if (Variables.language.equals("English")) {
				fileChooser.setTitle("Save new version");
	            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Archive", "*.jar"));
			}
			
			File file = fileChooser.showSaveDialog(new Stage());
			
			if(file != null){
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data);
				fos.close();
				
				return file;
			}
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return null;
	}
	
	static byte[] downloadUpdates(String link) {
		byte[] data = null;
		
		try {
			URLConnection conn = new URL(link).openConnection();
			
			conn.setUseCaches(false);
			
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
			
			InputStream inputStream = conn.getInputStream();
			
			data = readInputStream(inputStream, (double) conn.getContentLength());
			
			Platform.runLater(() -> {
				Dialogs.bar.setProgress(1);
				
				if (Variables.language.equals("English")) {
					Dialogs.text.setText("Downloaded (100.0%)");
				} else {
					Dialogs.text.setText("下载完成（100.0%）");
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return data;
	}
	
	static byte[] readInputStream(InputStream inputStream, Double length) {
		try {
			byte[] buffer = new byte[1];
			int len = 0;
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			double i = length / 100;
			int a = 1;
			
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
				
				if (a - i >= 0) {
					a = 1;
					
					downloadProgress++;
					
					Platform.runLater(() -> {
						Dialogs.bar.setProgress(downloadProgress / 100);
						
						if (Variables.language.equals("English")) {
							Dialogs.text.setText("Downloading (" + downloadProgress + "%)");
						} else {
							Dialogs.text.setText("下载中（" + downloadProgress + "%）");
						}
					});
				}
				
				a++;
			}
			
			bos.close();
			return bos.toByteArray();
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return null;
	}
	
	static String[] parseData(String data) {
		String[] datas = new String[5];
		
		try {
			data = data.substring(12);
			datas[0] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("\"")).substring(12);
			datas[1] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("\"")).substring(12);
			datas[2] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("\"")).substring(11);
			datas[3] = data.substring(0, data.indexOf("\""));
			
			data = data.substring(data.indexOf("\"")).substring(11);
			datas[4] = data.substring(0, data.indexOf("\""));
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return datas;
	}
	
	static String downloadJSON(String link) {
		String data = null;
		
		try {
			URLConnection conn = new URL(link).openConnection();
			
			conn.setUseCaches(false);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
			
			data = reader.readLine();
			
			reader.close();
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
		
		return data;
	}
}

class DownloadThread implements Runnable {
	String link;
	String altLink;
	
	public DownloadThread(String link, String altLink) {
		this.link = link;
		this.altLink = altLink;
	}
	
	@Override
	public void run() {
		try {
			byte[] datas = Updates.downloadUpdates(link);
			
			byte[] data[] = {datas};
			
			try {
				if (data[0] == null) {
					data[0] = Updates.downloadUpdates(altLink);
				}
			} catch (Exception e) {
				data[0] = Updates.downloadUpdates(altLink);
			}
			
			Platform.runLater(() -> {
				File file = Updates.saveFile(data[0]);
				
				if (file != null) {
					try {
						Desktop.getDesktop().open(file);
						
						System.exit(0);
					} catch (Exception e) {
						Dialogs.showExceptionDialog(e);
					}
				} else {
					Dialogs.showErrorDialog("A fatal error occurs, exiting now.", true);
					
					System.exit(1);
				}
			});
		} catch (Exception e) {
			Platform.runLater(() -> {
				Dialogs.showExceptionDialog(e);
			});
		}
	}
}
