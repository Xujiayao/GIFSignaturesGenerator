package tools;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Xujiayao
 *
 * @project GenerateProjectFlySignatures
 *
 * @package tools
 * @filename GetScreenSize.java
 */
public class GetScreenSize {
	
	static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double ScreenWidth1 = ScreenSize.getWidth();
	static double ScreenHeight1 = ScreenSize.getHeight();
	public static int ScreenWidth = (int)ScreenWidth1;
	public static int ScreenHeight = (int)ScreenHeight1;
}
