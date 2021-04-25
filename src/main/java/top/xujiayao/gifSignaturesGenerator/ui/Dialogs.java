package top.xujiayao.gifSignaturesGenerator.ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import top.xujiayao.gifSignaturesGenerator.Main;
import top.xujiayao.gifSignaturesGenerator.tools.Variables;

import java.awt.Desktop;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.Optional;

/**
 * @author Xujiayao
 */
public class Dialogs {

	public static void showUpdateDialog(String version, String data, String link) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("检查更新");
		alert.setHeaderText("有新版本可用！");

		alert.getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);

		Pane pane = new Pane();
		pane.setPrefSize(415, 260);

		Text text1 = new Text("GIF签名图生成工具 " + version + " 现在可用（您是 " + Variables.version + "）。");
		text1.setFont(new Font("Microsoft YaHei", 14));
		text1.setFill(Color.web("#323232"));
		text1.setWrappingWidth(395);
		text1.setLayoutX(10);
		text1.setLayoutY(22);

		Button button1 = new Button("点此下载");
		button1.setFont(new Font("Microsoft YaHei", 12));
		button1.setPrefSize(110, 25);
		button1.setLayoutX(10);
		button1.setLayoutY(42);

		Text text2 = new Text("更新说明：");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setFill(Color.web("#323232"));
		text2.setLayoutX(10);
		text2.setLayoutY(100);

		TextArea textArea = new TextArea(data.replaceAll("\\\\n", "\n"));
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefSize(395, 150);
		textArea.setLayoutX(10);
		textArea.setLayoutY(110);

		pane.getChildren().addAll(text1, button1, text2, textArea);

		button1.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI(link));
			} catch (Exception e1) {
				showExceptionDialog(e1);
			}
		});

		alert.getDialogPane().setContent(pane);

		alert.showAndWait();
	}

	public static void showPreferencesDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("首选项");

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		Tab tab1 = new Tab("缓存");
		tab1.setClosable(false);

		Tab tab2 = new Tab("更新");
		tab2.setClosable(false);

		TabPane tabPane = new TabPane(tab1, tab2);
		tabPane.setPrefSize(400, 250);

		Pane pane1 = new Pane();
		Pane pane2 = new Pane();

		final Text[] text2 = new Text[1];

		try {
			text2[0] = new Text("已用空间：\n\n" + BigDecimal.valueOf(FileUtils.sizeOfDirectory(Variables.dataFolder) / 1024.0).setScale(2, RoundingMode.HALF_UP) + " KB");
		} catch (Exception e) {
			if (e.getMessage().contains("exist")) {
				showErrorDialog("发生错误", "缓存目录不存在。");
			}
			text2[0] = new Text("已用空间：\n\n0.00 KB");
		}
		text2[0].setFont(new Font("Microsoft YaHei", 14));
		text2[0].setFill(Color.web("#323232"));
		text2[0].setLayoutX(0);
		text2[0].setLayoutY(22);

		Button button1 = new Button("清理缓存");
		button1.setFont(new Font("Microsoft YaHei", 12));
		button1.setPrefSize(110, 25);
		button1.setLayoutX(0);
		button1.setLayoutY(150);

		Text text3 = new Text("GIF签名图生成工具可以自动检查其更新版本。检查将在后台执行，并且只有在有新版本可用时才会通知您。");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setFill(Color.web("#323232"));
		text3.setWrappingWidth(360);
		text3.setLayoutX(0);
		text3.setLayoutY(22);

		Text text4 = new Text("检查频率：");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setFill(Color.web("#323232"));
		text4.setLayoutX(0);
		text4.setLayoutY(82);

		ComboBox<String> comboBox1 = new ComboBox<>();
		comboBox1.getItems().addAll("每次启动时", "从不");
		if (Variables.checkUpdates) {
			comboBox1.setValue("每次启动时");
		} else {
			comboBox1.setValue("从不");
		}
		comboBox1.setPrefSize(140, 20);
		comboBox1.setLayoutX(140);
		comboBox1.setLayoutY(67);

		Text text5 = new Text("检查测试版更新：");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setFill(Color.web("#323232"));
		text5.setLayoutX(0);
		text5.setLayoutY(112);

		ComboBox<String> comboBox2 = new ComboBox<>();
		comboBox2.getItems().addAll("检查", "不检查");
		if (Variables.checkBetaUpdates) {
			comboBox2.setValue("检查");
		} else {
			comboBox2.setValue("不检查");
		}
		comboBox2.setPrefSize(140, 20);
		comboBox2.setLayoutX(140);
		comboBox2.setLayoutY(97);
		if (comboBox1.getValue().equals("从不")) {
			comboBox2.setValue("不检查");
			comboBox2.setDisable(true);
		}

		Button button2 = new Button("现在检查");
		button2.setFont(new Font("Microsoft YaHei", 12));
		button2.setPrefSize(110, 25);
		button2.setLayoutX(0);
		button2.setLayoutY(150);

		pane1.getChildren().addAll(text2[0], button1);
		pane2.getChildren().addAll(text3, text4, comboBox1, text5, comboBox2, button2);

		tab1.setContent(pane1);
		tab2.setContent(pane2);

		dialog.getDialogPane().setContent(tabPane);

		button1.setOnAction(e -> {
			try {
				FileUtils.cleanDirectory(Variables.dataFolder);

				Variables.loginType = "";
				Variables.username = "";
				Variables.password = "";

				showMessageDialog("清理缓存", "缓存清理完毕。");
				text2[0].setText("已用空间：\n\n" + BigDecimal.valueOf(FileUtils.sizeOfDirectory(Variables.dataFolder) / 1024.0).setScale(2, RoundingMode.HALF_UP) + " KB");
			} catch (Exception e1) {
				showExceptionDialog(e1);
				showErrorDialog("发生错误", "缓存清理失败。");
			}
		});

		button2.setOnAction(e -> {
			Main.update.isManualRequest = true;
			new Thread(Main.update).start();
		});

		comboBox1.valueProperty().addListener((e, oldVal, newVal) -> {
			if (newVal.equals("从不")) {
				comboBox2.setValue("不检查");
				comboBox2.setDisable(true);
			} else {
				comboBox2.setDisable(false);
				if (Variables.checkBetaUpdates == true) {
					comboBox2.setValue("检查");
				} else {
					comboBox2.setValue("不检查");
				}
			}
		});

		Optional<ButtonType> result = dialog.showAndWait();

		result.ifPresent(e -> {
			if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
				Variables.checkUpdates = !comboBox1.getValue().equals("从不");
				Variables.checkBetaUpdates = !comboBox2.getValue().equals("不检查");

				Variables.saveConfig();
			}
		});
	}

	public static void showAboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setHeaderText("关于GIF签名图生成工具");

		Pane pane = new Pane();
		pane.setPrefSize(415, 305);

		ImageView imageView = new ImageView(SwingFXUtils.toFXImage(Variables.icons.get(8), null));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(48);
		imageView.setFitHeight(48);
		imageView.setLayoutX(10);
		imageView.setLayoutY(10);

		Text text1 = new Text("GIF签名图生成工具 " + Variables.version);
		text1.setFont(new Font("Microsoft YaHei", 18));
		text1.setFill(Color.web("#323232"));
		text1.setLayoutX(68);
		text1.setLayoutY(30);

		Hyperlink link1 = new Hyperlink("By Xujiayao");
		link1.setFont(new Font("Microsoft YaHei", 14));
		link1.setLayoutX(64);
		link1.setLayoutY(32);

		Separator separator1 = new Separator(Orientation.HORIZONTAL);
		separator1.setPrefWidth(395);
		separator1.setLayoutX(10);
		separator1.setLayoutY(68);

		Text text2 = new Text("本软件仅兼容 Windows 7 以上 64 位系统。");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setFill(Color.web("#323232"));
		text2.setLayoutX(10);
		text2.setLayoutY(88);

		Separator separator2 = new Separator(Orientation.HORIZONTAL);
		separator2.setPrefWidth(395);
		separator2.setLayoutX(10);
		separator2.setLayoutY(98);

		Text text3 = new Text("""
			  所有临时文件与用户输入的变量将被存储在 [%AppData%/Java Projects] 文件夹中。
			  			
			  再次打开软件时，软件将自动填充用户上一次输入的变量。您可以随时在软件首选项中删除这些文件。""");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setFill(Color.web("#323232"));
		text3.setWrappingWidth(395);
		text3.setLayoutX(10);
		text3.setLayoutY(118);

		Separator separator3 = new Separator(Orientation.HORIZONTAL);
		separator3.setPrefWidth(395);
		separator3.setLayoutX(10);
		separator3.setLayoutY(202);

		Text text4 = new Text("这是一个开源项目。");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setFill(Color.web("#323232"));
		text4.setLayoutX(10);
		text4.setLayoutY(222);

		Hyperlink link2 = new Hyperlink("GitHub (GIFSignaturesGenerator)");
		link2.setFont(new Font("Microsoft YaHei", 14));
		link2.setLayoutX(170);
		link2.setLayoutY(203);

		Text text5 = new Text("欢迎访问我的博客！");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setFill(Color.web("#323232"));
		text5.setLayoutX(10);
		text5.setLayoutY(242);

		Hyperlink link3 = new Hyperlink("Xujiayao's Blog");
		link3.setFont(new Font("Microsoft YaHei", 14));
		link3.setLayoutX(170);
		link3.setLayoutY(223);

		Separator separator4 = new Separator(Orientation.HORIZONTAL);
		separator4.setPrefWidth(395);
		separator4.setLayoutX(10);
		separator4.setLayoutY(252);

		Text text6 = new Text("Java version:\t\t" + System.getProperty("java.version") + " (" + Runtime.version() + ")\n"
			  + "JavaFX version:\t" + System.getProperty("javafx.version") + "\n"
			  + "OS version:\t\t" + System.getProperty("os.name") + " " + System.getProperty("os.arch"));
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setFill(Color.web("#778899"));
		text6.setLayoutX(10);
		text6.setLayoutY(272);

		pane.getChildren().addAll(imageView, text1, link1, separator1, text2, separator2, text3, separator3, text4, link2, text5, link3, separator4, text6);

		alert.getDialogPane().setContent(pane);

		link1.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/Xujiayao147"));
			} catch (Exception e1) {
				showExceptionDialog(e1);
			}
		});

		link2.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/Xujiayao147/GIFSignaturesGenerator"));
			} catch (Exception e1) {
				showExceptionDialog(e1);
			}
		});

		link3.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://blog.xujiayao.top/"));
			} catch (Exception e1) {
				showExceptionDialog(e1);
			}
		});

		alert.showAndWait();
	}

	public static void showMessageDialog(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public static void showExceptionDialog(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("异常");
		alert.setHeaderText(null);
		alert.setContentText("发生异常：\n" + e.getMessage());

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Text text = new Text("异常堆栈跟踪为：");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(text, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

	public static void showErrorDialog(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("错误");
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
}
