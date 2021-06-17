package top.xujiayao.gifsigngen.tools;

/**
 * @author Xujiayao
 */
public class CustomException extends Exception {

	public CustomException(String e) {
		super("自定义：" + e);
	}
}
