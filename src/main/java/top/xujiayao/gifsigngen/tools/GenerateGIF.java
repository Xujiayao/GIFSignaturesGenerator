package top.xujiayao.gifsigngen.tools;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import javafx.application.Platform;
import top.xujiayao.gifsigngen.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class GenerateGIF {

	private GenerateGIF() {
		throw new IllegalStateException("工具类");
	}

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

				Files.delete(temp.toPath());
			}

			return true;
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}

		return false;
	}

	private static BufferedImage[] parse(File[] files) throws IOException {
		BufferedImage[] bi = new BufferedImage[5];

		for (int i = 0; i < 5; i++) {
			bi[i] = ImageIO.read(files[i]);
		}

		return bi;
	}
}
