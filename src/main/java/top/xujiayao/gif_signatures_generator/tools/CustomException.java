package top.xujiayao.gif_signatures_generator.tools;

/**
 * @author Xujiayao
 */
public class CustomException extends Exception {

	public CustomException(String e) {
		super("自定义：" + e);
	}
}
