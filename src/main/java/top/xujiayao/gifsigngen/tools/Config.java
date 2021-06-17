package top.xujiayao.gifsigngen.tools;

import com.google.gson.annotations.Expose;

/**
 * @author Xujiayao
 */
public class Config {

	@Expose
	public Preferences preferences = new Preferences();

	@Expose
	public UserVariables userVariables = new UserVariables();

	public static class Preferences {

		// 是否每次启动时检查更新
		@Expose
		public boolean checkUpdates = true;

		// 是否检查测试版更新
		@Expose
		public boolean checkBetaUpdates = true;
	}

	public static class UserVariables {

		// 登录方式
		@Expose
		public String loginType = "projectFLY";

		// 用户名和密码
		@Expose
		public String username = "";
		@Expose
		public String password = "";

		// 用户选择的签名图样式
		@Expose
		public int useStyleProjectFly = 1;
	}
}
