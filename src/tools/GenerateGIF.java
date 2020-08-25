package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

import log.RunLog;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename GenerateGIF.java
 */
public class GenerateGIF {
	
	private static File file;
	
	/**
	 * @method generateGIF
	 * @usage 这个类从这里调用
	 */
	public static void generateGIF() {
		try {
			String filePath = GetVariables.folderFile + File.separatorChar + "png";
			
			File file1 = new File(filePath + File.separatorChar + "a1.png");
			File file2 = new File(filePath + File.separatorChar + "a2.png");
			File file3 = new File(filePath + File.separatorChar + "a3.png");
			File file4 = new File(filePath + File.separatorChar + "a4.png");
			File file5 = new File(filePath + File.separatorChar + "a5.png");
			
			File[] files = {file1, file2, file3, file4, file5};
			
			File gif = generatePreview(files);
			gif.createNewFile();
			
			file.delete();
			System.gc();
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
	}
	
	/**
	 * @method generatePreview
	 * @usage 生成用于储存gif的临时文件
	 */
	private static File generatePreview(File[] files) {
		file = null;
		
		try {
			BufferedImage[] images = null;

			images = parse(files);

			file = File.createTempFile("temp_", ".gif");
			
			AnimatedGifEncoder encoder = new AnimatedGifEncoder();
			encoder.setRepeat(0);

			encoder.start(new FileOutputStream(GetVariables.folderFile + File.separatorChar + "signature.gif"));
			
			for (BufferedImage image : images) {
				encoder.setDelay(2000);
				encoder.addFrame(image);
			}
			encoder.finish();
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return file;
	}
	
	/**
	 * @method parse
	 * @usage 制作BufferedImage组
	 */
	private static BufferedImage[] parse(File[] files) {
		BufferedImage[] bi = new BufferedImage[5];

		try {
			for (int index = 0; index < 5; index++) {
				bi[index] = ImageIO.read(files[index]);
			}
		} catch (Exception e) {
			RunLog.logAppend("出现异常：" + e.getMessage());
			ShowDialogs.showErrorDialog(e);
		}
		return bi;
	}
}
