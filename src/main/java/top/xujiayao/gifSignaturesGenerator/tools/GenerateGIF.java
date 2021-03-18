package top.xujiayao.gifSignaturesGenerator.tools;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import javafx.application.Platform;
import top.xujiayao.gifSignaturesGenerator.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class GenerateGIF {

	public static boolean generate() {
		try {
			File[] files = new File(Variables.dataFolder + "/temp").listFiles();

			if (Objects.requireNonNull(files).length == 5) {
				BufferedImage[] images = parse(files);

				File temp = File.createTempFile("temp_", ".gif");

				AnimatedGifEncoder encoder = new AnimatedGifEncoder();
				encoder.setRepeat(0);

				encoder.start(new FileOutputStream(Variables.dataFolder + "/signature.gif"));

				for (BufferedImage image : images) {
					encoder.setDelay(2000);
					encoder.addFrame(image);
				}

				encoder.finish();

				if (!temp.delete()) {
					throw new Exception("自定义：无法删除文件");
				}
			}

			System.gc();

			return true;
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return false;
	}

	private static BufferedImage[] parse(File[] files) throws Exception {
		BufferedImage[] bi = new BufferedImage[5];

		for (int i = 0; i < 5; i++) {
			bi[i] = ImageIO.read(files[i]);
		}

		return bi;
	}
}
