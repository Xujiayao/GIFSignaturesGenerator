package io.gitee.xujiayao147.gifSignaturesGenerator.ui;

import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;

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

	public void showPreferencesDialog() {
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

		Text text1 = new Text("缓存");
		text1.setFont(new Font("Microsoft YaHei", 14));
		text1.setFill(Color.web("#323232"));
		text1.setLayoutX(0);
		text1.setLayoutY(22);

		Separator separator1 = new Separator(Orientation.HORIZONTAL);
		separator1.setPrefWidth(340);
		separator1.setLayoutX(40);
		separator1.setLayoutY(17);

		final Text[] text2 = new Text[1];

		try {
			text2[0] = new Text("已用空间：\n\n" + FileUtils.sizeOfDirectory(Main.variables.dataFolder) + " 字节 (B)\n" +
				  BigDecimal.valueOf(FileUtils.sizeOfDirectory(Main.variables.dataFolder) / 1024.0).setScale(2, RoundingMode.HALF_UP) + " 千字节 (KiB)\n" +
				  BigDecimal.valueOf(FileUtils.sizeOfDirectory(Main.variables.dataFolder) / 1024.0 / 1024.0).setScale(2, RoundingMode.HALF_UP) + " 兆字节 (MiB)");
		} catch (Exception e) {
			if (e.getMessage().contains("exist"))
				showErrorDialog("发生错误", "缓存目录不存在。");
			text2[0] = new Text("已用空间：\n\n0 字节 (B)\n0 千字节 (KiB)\n0 兆字节 (MiB)");
		}
		text2[0].setFont(new Font("Microsoft YaHei", 14));
		text2[0].setFill(Color.web("#323232"));
		text2[0].setLayoutX(0);
		text2[0].setLayoutY(52);

		Button button1 = new Button("清理缓存");
		button1.setFont(new Font("Microsoft YaHei", 12));
		button1.setPrefSize(110, 25);
		button1.setLayoutX(0);
		button1.setLayoutY(160);

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

		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("每次启动时", "从不");
		comboBox.setValue("每次启动时");
		comboBox.setPrefSize(140, 20);
		comboBox.setLayoutX(140);
		comboBox.setLayoutY(67);

		Button button2 = new Button("现在检查");
		button2.setFont(new Font("Microsoft YaHei", 12));
		button2.setPrefSize(110, 25);
		button2.setLayoutX(0);
		button2.setLayoutY(110);

//		if (Variables.checkUpdates == false)
//			choiceBox.setValue("从不");

		pane1.getChildren().addAll(text1, separator1, text2[0], button1);
		pane2.getChildren().addAll(text3, text4, comboBox, button2);

		tab1.setContent(pane1);
		tab2.setContent(pane2);

		dialog.getDialogPane().setContent(tabPane);

		button1.setOnAction(e -> {
			try {
				FileUtils.cleanDirectory(Main.variables.dataFolder);
				showMessageDialog("清理缓存", "缓存清理完毕。");
				text2[0].setText("已用空间：\n\n" + FileUtils.sizeOfDirectory(Main.variables.dataFolder) + " 字节 (B)\n" +
					  BigDecimal.valueOf(FileUtils.sizeOfDirectory(Main.variables.dataFolder) / 1024.0).setScale(2, RoundingMode.HALF_UP) + " 千字节 (KiB)\n" +
					  BigDecimal.valueOf(FileUtils.sizeOfDirectory(Main.variables.dataFolder) / 1024.0 / 1024.0).setScale(2, RoundingMode.HALF_UP) + " 兆字节 (MiB)");
			} catch (Exception e1) {
				showExceptionDialog(e1);
				showErrorDialog("发生错误", "缓存清理失败。");
			}
		});

		button2.setOnAction(e -> {
			//Update.start(false);
		});

		Optional<ButtonType> result = dialog.showAndWait();

//		result.ifPresent(usernamePassword -> {
//			if (result.get().getButtonData() == ButtonData.OK_DONE) {
//				String language;
//
//				if (rb1.isSelected()) {
//					Variables.saveLanguage = "中文";
//					language = "中文";
//				} else {
//					Variables.saveLanguage = "English";
//					language = "English";
//				}
//
//				if (choiceBox.getValue().equals("从不") || choiceBox.getValue().equals("Never")) {
//					Variables.saveCheckUpdates = false;
//				} else {
//					Variables.saveCheckUpdates = true;
//				}
//
//				Variables.saveVariables();
//
//				File file = null;
//
//				try {
//					file = new File(System.getProperty("user.dir") + "/PFSignaturesGenerator.exe");
//				} catch (Exception e) {
//					Dialogs.showExceptionDialog(e);
//				}
//
//				if (file.exists()) {
//					boolean confirmRestart;
//
//					if (language.equals("English")) {
//						confirmRestart = Dialogs.showConfirmDialog("Do you want to restart the application now to apply the new settings?", "Confirm", null, true);
//					} else {
//						confirmRestart = Dialogs.showConfirmDialog("您是否想要现在重新启动应用程序以应用新设置？", "确认", null, false);
//					}
//
//					if (confirmRestart) {
//						try {
//							Desktop.getDesktop().open(file);
//
//							System.exit(0);
//						} catch (Exception e) {
//							Dialogs.showExceptionDialog(e);
//						}
//					}
//				} else {
//					if (language.equals("English")) {
//						Dialogs.showErrorDialog("Cannot restart the application, you have to restart the application manually to apply the new settings.", true);
//					} else {
//						Dialogs.showErrorDialog("无法重启应用程序，您需要手动重启应用程序以应用新设置。", false);
//					}
//				}
//			}
//		});
	}

	public void showAboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setHeaderText("关于GIF签名图生成工具");

		Pane pane = new Pane();
		pane.setPrefSize(400, 260);

		ImageView imageView = new ImageView(SwingFXUtils.toFXImage(Main.variables.icons.get(8), null));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(48);
		imageView.setFitHeight(48);
		imageView.setLayoutX(10);
		imageView.setLayoutY(10);

		Text text1 = new Text("GIF签名图生成工具 " + Main.variables.version);
		text1.setFont(new Font("Microsoft YaHei", 18));
		text1.setFill(Color.web("#323232"));
		text1.setLayoutX(68);
		text1.setLayoutY(30);

		Hyperlink link1 = new Hyperlink("By Xujiayao");
		link1.setFont(new Font("Microsoft YaHei", 14));
		link1.setLayoutX(64);
		link1.setLayoutY(32);

		Separator separator1 = new Separator(Orientation.HORIZONTAL);
		separator1.setPrefWidth(380);
		separator1.setLayoutX(10);
		separator1.setLayoutY(68);

		Text text3 = new Text("本软件仅兼容 Windows 7 以上 64 位系统。");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setFill(Color.web("#323232"));
		text3.setLayoutX(10);
		text3.setLayoutY(88);

		Separator separator2 = new Separator(Orientation.HORIZONTAL);
		separator2.setPrefWidth(380);
		separator2.setLayoutX(10);
		separator2.setLayoutY(98);

		Text text4 = new Text("""
			  软件首选项与用户存储的变量将被存储在 [%AppData%/Java Projects] 文件夹中。
			  			  
			  已存储的变量将自动被填充，您可以在软件首选项中删除这些缓存。""");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setFill(Color.web("#323232"));
		text4.setWrappingWidth(380);
		text4.setLayoutX(10);
		text4.setLayoutY(118);

		Separator separator3 = new Separator(Orientation.HORIZONTAL);
		separator3.setPrefWidth(380);
		separator3.setLayoutX(10);
		separator3.setLayoutY(202);

		Text text5 = new Text("这是一个开源项目。");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setFill(Color.web("#323232"));
		text5.setLayoutX(10);
		text5.setLayoutY(222);

		Hyperlink link2 = new Hyperlink("GitHub (GIFSignaturesGenerator)");
		link2.setFont(new Font("Microsoft YaHei", 14));
		link2.setLayoutX(150);
		link2.setLayoutY(203);

		Text text6 = new Text("欢迎访问我的博客！");
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setFill(Color.web("#323232"));
		text6.setLayoutX(10);
		text6.setLayoutY(242);

		Hyperlink link3 = new Hyperlink("Xujiayao's Blog");
		link3.setFont(new Font("Microsoft YaHei", 14));
		link3.setLayoutX(150);
		link3.setLayoutY(223);

		pane.getChildren().addAll(imageView, text1, link1, separator1, text3, separator2, text4, separator3, text5, link2, text6, link3);

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
				Desktop.getDesktop().browse(new URI("https://xujiayao147.gitee.io/"));
			} catch (Exception e1) {
				showExceptionDialog(e1);
			}
		});

		alert.showAndWait();
	}

	public void showMessageDialog(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public void showExceptionDialog(Exception e) {
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

	public void showErrorDialog(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("错误");
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
}
