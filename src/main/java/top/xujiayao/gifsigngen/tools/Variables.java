package top.xujiayao.gifsigngen.tools;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import net.sf.image4j.codec.ico.ICODecoder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import top.xujiayao.gifsigngen.ui.Dialogs;

import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class Variables {

	// 软件版本
	public static final String VERSION = "21w25b";

	// 最新的正式版版本
	public static final String LATEST_RELEASE_VERSION = "1.0.0";

	// 软件使用到的字体
	public static final String[] FONTS = {"Microsoft YaHei"};

	// 更新功能使用的文件 version.json 的下载路径
	public static final String CHECK_UPDATE_LINK = "https://cdn.jsdelivr.net/gh/Xujiayao/GIFSignaturesGenerator@master/update/version.json";

	// 屏幕宽度和高度
	public static double screenWidth;
	public static double screenHeight;

	// 存放缓存的文件夹
	public static File dataFolder;

	// 软件图标
	public static List<BufferedImage> icons;

	// 用户头像
	public static BufferedImage avatar;

	// 解析过的图床返回的内容
	public static String[] uploadData;

	// 系统剪贴板
	public static Clipboard clipboard;

	// 解析过的一言语句
	public static String[] hitokotoData;

	// 显示的一言语句
	public static String displayHitokotoData = "加载中...";

	// HTTPClient 插件
	public static CloseableHttpClient httpClient;
	public static CloseableHttpResponse response;

	// projectFLY 需要使用的变量
	public static ProjectFly projectFlyData;

	public static class ProjectFly {

		// 解析过的 LoginAPI 返回的数据
		public String[] loginData;

		// 解析过的 ProfileAPI 返回的数据
		public String[] profileData;

		// 解析过的 LogbookAPI 返回的数据
		public String[] logbookData;

		// 解析过的 PassportAPI 返回的数据
		public String passportData;
	}

	public static void init() {
		StringBuilder error = new StringBuilder("发生致命错误，程序将立即退出。\n\n");

		// 分析屏幕宽高
		try {
			Rectangle2D screen = Screen.getPrimary().getBounds();
			screenWidth = screen.getWidth();
			screenHeight = screen.getHeight();

			if ((screenWidth == 0) || (screenHeight == 0)) {
				throw new CustomException("屏幕宽度或高度为 0");
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法获取屏幕宽高\n");
		}

		// 分析文件夹是否存在并按需创建文件夹
		try {
			dataFolder = new File(System.getenv("APPDATA") + "/Java Projects");

			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				if (!dataFolder.mkdir()) {
					throw new CustomException("无法创建文件夹");
				}
			}

			dataFolder = new File(dataFolder.toString() + "/GIFSignaturesGenerator");

			if (!dataFolder.exists() || !dataFolder.isDirectory()) {
				if (!dataFolder.mkdir()) {
					throw new CustomException("无法创建文件夹");
				}
			}

			File tempFolder = new File(dataFolder.toString() + "/temp");

			if (!tempFolder.exists() || !tempFolder.isDirectory()) {
				if (!tempFolder.mkdir()) {
					throw new CustomException("无法创建文件夹");
				}
			}
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法分析文件夹是否存在或创建文件夹\n");
		}

		// 加载图标
		try {
			icons = ICODecoder.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.ico")));
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载图标\n");
		}

		// 加载默认头像
		try {
			avatar = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("no-profile-image.png")));
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载默认头像\n");
		}

		// 加载系统剪贴板
		try {
			clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法加载系统剪贴板\n");
		}

		// 初始化 HTTPClient 插件
		try {
			httpClient = HttpClients.createDefault();
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
			error.append("-> 无法初始化 HTTPClient 插件\n");
		}

		if (error.indexOf("->") != -1) {
			error.append("\n如果你认为这是一个错误，请告诉我。");

			Dialogs.showErrorDialog("发生致命错误", error.toString());

			System.exit(1);
		}
	}
}
