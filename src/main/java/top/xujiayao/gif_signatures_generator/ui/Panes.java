package top.xujiayao.gif_signatures_generator.ui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import top.xujiayao.gif_signatures_generator.Main;
import top.xujiayao.gif_signatures_generator.tools.Variables;

import javax.imageio.ImageIO;
import java.awt.Desktop;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import java.util.Objects;

/**
 * @author Xujiayao
 */
public class Panes {

	public int paneShowing;

	public TextField field5;

	public Pane pane1() {
		paneShowing = 1;

		Pane pane = new Pane();
		pane.setPrefSize(620, 518);
		pane.setLayoutX(180);
		pane.setLayoutY(40);
		pane.setStyle("-fx-background-color: #FFF");

		Text title = new Text("欢迎使用GIF签名图生成工具！");
		title.setFont(new Font("Microsoft YaHei Bold", 18));
		title.setWrappingWidth(600);
		title.setLayoutX(10);
		title.setLayoutY(28);

		Text text0 = new Text("此向导将引导您逐步收集生成您的签名图所需的全部信息。");
		text0.setFont(new Font(Variables.FONTS[0], 14));
		text0.setWrappingWidth(600);
		text0.setLayoutX(10);
		text0.setLayoutY(67);

		Text text00 = new Text("请检查您的解析结果。如果有任何错误，请告诉我。");
		text00.setFont(new Font(Variables.FONTS[0], 14));
		text00.setWrappingWidth(600);
		text00.setLayoutX(10);
		text00.setLayoutY(96);

		Text text1 = new Text("显示名称：");
		text1.setFont(new Font(Variables.FONTS[0], 14));
		text1.setWrappingWidth(170);
		text1.setLayoutX(10);
		text1.setLayoutY(138);

		TextField field1 = new TextField(Main.getProjectFlyData().getLoginData()[1]);
		field1.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field1.setPrefSize(420, 25);
		field1.setFont(new Font(Variables.FONTS[0], 12));
		field1.setEditable(false);
		field1.setLayoutX(190);
		field1.setLayoutY(120);

		Text text2 = new Text("用户名：");
		text2.setFont(new Font(Variables.FONTS[0], 14));
		text2.setWrappingWidth(170);
		text2.setLayoutX(10);
		text2.setLayoutY(173);

		TextField field2 = new TextField(Main.getProjectFlyData().getLoginData()[0]);
		field2.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field2.setPrefSize(420, 25);
		field2.setFont(new Font(Variables.FONTS[0], 12));
		field2.setEditable(false);
		field2.setLayoutX(190);
		field2.setLayoutY(155);

		Text text3 = new Text("总小时数：");
		text3.setFont(new Font(Variables.FONTS[0], 14));
		text3.setWrappingWidth(170);
		text3.setLayoutX(10);
		text3.setLayoutY(208);

		TextField field3 = new TextField(Main.getProjectFlyData().getProfileData()[3]);
		field3.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field3.setPrefSize(420, 25);
		field3.setFont(new Font(Variables.FONTS[0], 12));
		field3.setEditable(false);
		field3.setLayoutX(190);
		field3.setLayoutY(190);

		Text text4 = new Text("总航班数：");
		text4.setFont(new Font(Variables.FONTS[0], 14));
		text4.setWrappingWidth(170);
		text4.setLayoutX(10);
		text4.setLayoutY(243);

		TextField field4 = new TextField(Main.getProjectFlyData().getProfileData()[2]);
		field4.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field4.setPrefSize(420, 25);
		field4.setFont(new Font(Variables.FONTS[0], 12));
		field4.setEditable(false);
		field4.setLayoutX(190);
		field4.setLayoutY(225);

		Text text5 = new Text("个人简介：（可编辑）");
		text5.setFont(new Font(Variables.FONTS[0], 14));
		text5.setWrappingWidth(170);
		text5.setLayoutX(10);
		text5.setLayoutY(278);

		field5 = new TextField(Main.getProjectFlyData().getProfileData()[0]);
		field5.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field5.setPrefSize(420, 25);
		field5.setFont(new Font(Variables.FONTS[0], 12));
		field5.setLayoutX(190);
		field5.setLayoutY(260);

		Text text6 = new Text("喜爱的航空器：");
		text6.setFont(new Font(Variables.FONTS[0], 14));
		text6.setWrappingWidth(170);
		text6.setLayoutX(10);
		text6.setLayoutY(313);

		TextField field6 = new TextField(Main.getProjectFlyData().getProfileData()[5] + " / " + Main.getProjectFlyData().getProfileData()[4]);
		field6.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field6.setPrefSize(420, 25);
		field6.setFont(new Font(Variables.FONTS[0], 12));
		field6.setEditable(false);
		field6.setLayoutX(190);
		field6.setLayoutY(295);

		Text text7 = new Text("喜爱的机场：");
		text7.setFont(new Font(Variables.FONTS[0], 14));
		text7.setWrappingWidth(170);
		text7.setLayoutX(10);
		text7.setLayoutY(348);

		TextField field7 = new TextField(Main.getProjectFlyData().getLogbookData()[0]);
		field7.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field7.setPrefSize(420, 25);
		field7.setFont(new Font(Variables.FONTS[0], 12));
		field7.setEditable(false);
		field7.setLayoutX(190);
		field7.setLayoutY(330);

		Text text8 = new Text("喜爱的路线：");
		text8.setFont(new Font(Variables.FONTS[0], 14));
		text8.setWrappingWidth(170);
		text8.setLayoutX(10);
		text8.setLayoutY(383);

		TextField field8 = new TextField(Main.getProjectFlyData().getLogbookData()[1]);
		field8.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field8.setPrefSize(420, 25);
		field8.setFont(new Font(Variables.FONTS[0], 12));
		field8.setEditable(false);
		field8.setLayoutX(190);
		field8.setLayoutY(365);

		Text text9 = new Text("已访问国家：");
		text9.setFont(new Font(Variables.FONTS[0], 14));
		text9.setWrappingWidth(170);
		text9.setLayoutX(10);
		text9.setLayoutY(418);

		TextField field9 = new TextField(Main.getProjectFlyData().getPassportData());
		field9.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field9.setPrefSize(420, 25);
		field9.setFont(new Font(Variables.FONTS[0], 12));
		field9.setEditable(false);
		field9.setLayoutX(190);
		field9.setLayoutY(400);

		Text text10 = new Text("平均接地率：");
		text10.setFont(new Font(Variables.FONTS[0], 14));
		text10.setWrappingWidth(170);
		text10.setLayoutX(10);
		text10.setLayoutY(453);

		TextField field10 = new TextField(Main.getProjectFlyData().getProfileData()[6]);
		field10.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field10.setPrefSize(420, 25);
		field10.setFont(new Font(Variables.FONTS[0], 12));
		field10.setEditable(false);
		field10.setLayoutX(190);
		field10.setLayoutY(435);

		Text text11 = new Text("已完成成就：");
		text11.setFont(new Font(Variables.FONTS[0], 14));
		text11.setWrappingWidth(170);
		text11.setLayoutX(10);
		text11.setLayoutY(488);

		TextField field11 = new TextField(Main.getProjectFlyData().getProfileData()[1]);
		field11.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field11.setPrefSize(420, 25);
		field11.setFont(new Font(Variables.FONTS[0], 12));
		field11.setEditable(false);
		field11.setLayoutX(190);
		field11.setLayoutY(470);

		pane.getChildren().addAll(title, text0, text00, text1, field1, text2, field2, text3, field3, text4, field4, text5, field5, text6, field6, text7, field7, text8, field8, text9, field9, text10, field10, text11, field11);

		return pane;
	}

	public Pane pane2() {
		paneShowing = 2;

		Pane pane = new Pane();
		pane.setPrefSize(620, 518);
		pane.setLayoutX(180);
		pane.setLayoutY(40);
		pane.setStyle("-fx-background-color: #FFF");

		Text title = new Text("选择签名图样式");
		title.setFont(new Font("Microsoft YaHei Bold", 18));
		title.setWrappingWidth(600);
		title.setLayoutX(10);
		title.setLayoutY(28);

		Text text0 = new Text("请根据自己的喜好选择以下其中一款签名图样式。");
		text0.setFont(new Font(Variables.FONTS[0], 14));
		text0.setWrappingWidth(600);
		text0.setLayoutX(10);
		text0.setLayoutY(67);

		Text text00 = new Text("欢迎设计其他签名图样式并发送给我，我会在优化后添加到软件里。");
		text00.setFont(new Font(Variables.FONTS[0], 14));
		text00.setWrappingWidth(600);
		text00.setLayoutX(10);
		text00.setLayoutY(96);

		ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("projectFLY v2");
		rb1.setFont(new Font(Variables.FONTS[0], 14));
		rb1.setLayoutX(10);
		rb1.setLayoutY(120);
		rb1.setToggleGroup(group);
		rb1.setUserData(1);

		ImageView imageView1 = null;
		try {
			imageView1 = new ImageView(SwingFXUtils.toFXImage(ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("pf-signature-1.png"))), null));
		} catch (Exception e) {
			Dialogs.showExceptionDialog(e);
		}
		Objects.requireNonNull(imageView1).setPreserveRatio(true);
		imageView1.setSmooth(true);
		imageView1.setFitWidth(600);
		imageView1.setFitHeight(40);
		imageView1.setLayoutX(10);
		imageView1.setLayoutY(150);

		RadioButton rb2 = new RadioButton("projectFLY v3");
		rb2.setFont(new Font(Variables.FONTS[0], 14));
		rb2.setLayoutX(10);
		rb2.setLayoutY(220);
		rb2.setToggleGroup(group);
		rb2.setUserData(2);
		rb2.setDisable(true);

		switch (Variables.useStyleProjectFly) {
			case 1 -> rb1.setSelected(true);
			case 2 -> rb2.setSelected(true);
		}

//		ImageView imageView2 = null;
//		try {
//			imageView2 = new ImageView(SwingFXUtils.toFXImage(ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("pf-signature-1.png"))), null));
//		} catch (Exception e) {
//			Dialogs.showExceptionDialog(e);
//		}
//		Objects.requireNonNull(imageView2).setPreserveRatio(true);
//		imageView2.setSmooth(true);
//		imageView2.setFitWidth(600);
//		imageView2.setFitHeight(40);
//		imageView2.setLayoutX(10);
//		imageView2.setLayoutY(250);

		pane.getChildren().addAll(title, text0, text00, rb1, imageView1, rb2);

		group.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
			if (group.getSelectedToggle() != null) {
				Variables.useStyleProjectFly = (int) group.getSelectedToggle().getUserData();
			}
		});

		return pane;
	}

	public Pane pane3() {
		paneShowing = 3;

		Pane pane = new Pane();
		pane.setPrefSize(620, 518);
		pane.setLayoutX(180);
		pane.setLayoutY(40);
		pane.setStyle("-fx-background-color: #FFF");

		Text title = new Text("完成");
		title.setFont(new Font("Microsoft YaHei Bold", 18));
		title.setWrappingWidth(600);
		title.setLayoutX(10);
		title.setLayoutY(28);

		Text text0 = new Text("签名图已经成功生成，并存储在本地目录。签名图也已经上传至 SM.MS 图床。");
		text0.setFont(new Font(Variables.FONTS[0], 14));
		text0.setWrappingWidth(600);
		text0.setLayoutX(10);
		text0.setLayoutY(67);

		Text text00 = new Text("以下是图床返回的内容的解析结果。欢迎再次使用本软件！");
		text00.setFont(new Font(Variables.FONTS[0], 14));
		text00.setWrappingWidth(600);
		text00.setLayoutX(10);
		text00.setLayoutY(96);

		Text text1 = new Text("请求状态：");
		text1.setFont(new Font(Variables.FONTS[0], 14));
		text1.setWrappingWidth(170);
		text1.setLayoutX(10);
		text1.setLayoutY(138);

		TextField field1 = new TextField(Variables.uploadData[0]);
		field1.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field1.setPrefSize(420, 25);
		field1.setFont(new Font(Variables.FONTS[0], 12));
		field1.setEditable(false);
		field1.setLayoutX(190);
		field1.setLayoutY(120);

		Text text2 = new Text("请求状态码：");
		text2.setFont(new Font(Variables.FONTS[0], 14));
		text2.setWrappingWidth(170);
		text2.setLayoutX(10);
		text2.setLayoutY(173);

		TextField field2 = new TextField(Variables.uploadData[1]);
		field2.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field2.setPrefSize(420, 25);
		field2.setFont(new Font(Variables.FONTS[0], 12));
		field2.setEditable(false);
		field2.setLayoutX(190);
		field2.setLayoutY(155);

		Text text3 = new Text("信息：");
		text3.setFont(new Font(Variables.FONTS[0], 14));
		text3.setWrappingWidth(170);
		text3.setLayoutX(10);
		text3.setLayoutY(208);

		TextField field3 = new TextField(Variables.uploadData[2]);
		field3.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field3.setPrefSize(420, 25);
		field3.setFont(new Font(Variables.FONTS[0], 12));
		field3.setEditable(false);
		field3.setLayoutX(190);
		field3.setLayoutY(190);

		Text text4 = new Text("图片链接：");
		text4.setFont(new Font(Variables.FONTS[0], 14));
		text4.setWrappingWidth(170);
		text4.setLayoutX(10);
		text4.setLayoutY(243);

		TextField field4 = new TextField(Variables.uploadData[3]);
		field4.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field4.setPrefSize(360, 25);
		field4.setFont(new Font(Variables.FONTS[0], 12));
		field4.setEditable(false);
		field4.setLayoutX(190);
		field4.setLayoutY(225);

		Button button1 = new Button("访问");
		button1.setFont(new Font(Variables.FONTS[0], 12));
		button1.setStyle("-fx-background-color: #24292E");
		button1.setTextFill(Color.web("#FFF"));
		button1.setPrefSize(50, 25);
		button1.setLayoutX(560);
		button1.setLayoutY(225);

		if (field4.getText().equals("N/A")) {
			button1.setDisable(true);
		}

		Text text5 = new Text("图片删除链接：");
		text5.setFont(new Font(Variables.FONTS[0], 14));
		text5.setWrappingWidth(170);
		text5.setLayoutX(10);
		text5.setLayoutY(278);

		TextField field5 = new TextField(Variables.uploadData[4]);
		field5.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field5.setPrefSize(360, 25);
		field5.setFont(new Font(Variables.FONTS[0], 12));
		field5.setEditable(false);
		field5.setLayoutX(190);
		field5.setLayoutY(260);

		Button button2 = new Button("访问");
		button2.setFont(new Font(Variables.FONTS[0], 12));
		button2.setStyle("-fx-background-color: #24292E");
		button2.setTextFill(Color.web("#FFF"));
		button2.setPrefSize(50, 25);
		button2.setLayoutX(560);
		button2.setLayoutY(260);

		if (field5.getText().equals("N/A")) {
			button2.setDisable(true);
		}

		Text text000 = new Text("复制以下 BBCode 代码，粘贴到论坛设置的个人签名文本框，点击保存即可：");
		text000.setFont(new Font(Variables.FONTS[0], 14));
		text000.setWrappingWidth(600);
		text000.setLayoutX(10);
		text000.setLayoutY(320);

		Text text6 = new Text("BBCode 代码：");
		text6.setFont(new Font(Variables.FONTS[0], 14));
		text6.setWrappingWidth(170);
		text6.setLayoutX(10);
		text6.setLayoutY(358);

		TextField field6 = new TextField(Variables.uploadData[5]);
		field6.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field6.setPrefSize(360, 25);
		field6.setFont(new Font(Variables.FONTS[0], 12));
		field6.setEditable(false);
		field6.setLayoutX(190);
		field6.setLayoutY(340);

		Button button3 = new Button("复制");
		button3.setFont(new Font(Variables.FONTS[0], 12));
		button3.setStyle("-fx-background-color: #24292E");
		button3.setTextFill(Color.web("#FFF"));
		button3.setPrefSize(50, 25);
		button3.setLayoutX(560);
		button3.setLayoutY(340);

		if (field6.getText().equals("N/A")) {
			button3.setDisable(true);
		}

		Text text0000 = new Text("其他标记语言：");
		text0000.setFont(new Font(Variables.FONTS[0], 14));
		text0000.setWrappingWidth(600);
		text0000.setLayoutX(10);
		text0000.setLayoutY(400);

		Text text7 = new Text("Markdown 代码：");
		text7.setFont(new Font(Variables.FONTS[0], 14));
		text7.setWrappingWidth(170);
		text7.setLayoutX(10);
		text7.setLayoutY(438);

		TextField field7 = new TextField(Variables.uploadData[6]);
		field7.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		field7.setPrefSize(360, 25);
		field7.setFont(new Font(Variables.FONTS[0], 12));
		field7.setEditable(false);
		field7.setLayoutX(190);
		field7.setLayoutY(420);

		Button button4 = new Button("复制");
		button4.setFont(new Font(Variables.FONTS[0], 12));
		button4.setStyle("-fx-background-color: #24292E");
		button4.setTextFill(Color.web("#FFF"));
		button4.setPrefSize(50, 25);
		button4.setLayoutX(560);
		button4.setLayoutY(420);

		if (field7.getText().equals("N/A")) {
			button4.setDisable(true);
		}

		pane.setOnMouseMoved(e -> {
			pane.setCursor(Cursor.DEFAULT);

			button1.setStyle("-fx-background-color: #24292E");
			button2.setStyle("-fx-background-color: #24292E");
			button3.setStyle("-fx-background-color: #24292E");
			button4.setStyle("-fx-background-color: #24292E");
		});

		button1.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI(field4.getText()));
			} catch (Exception e1) {
				Dialogs.showExceptionDialog(e1);
			}
		});

		button2.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI(field5.getText()));
			} catch (Exception e1) {
				Dialogs.showExceptionDialog(e1);
			}
		});

		button3.setOnAction(e -> {
			try {
				Variables.clipboard.setContents(new StringSelection(field6.getText()), null);
				Dialogs.showMessageDialog("复制", "复制成功。");
			} catch (Exception e1) {
				Dialogs.showExceptionDialog(e1);
			}
		});

		button4.setOnAction(e -> {
			try {
				Variables.clipboard.setContents(new StringSelection(field7.getText()), null);
				Dialogs.showMessageDialog("复制", "复制成功。");
			} catch (Exception e1) {
				Dialogs.showExceptionDialog(e1);
			}
		});

		button1.setOnMouseEntered(e -> {
			pane.setCursor(Cursor.HAND);
			button1.setStyle("-fx-background-color: #414449");
		});

		button2.setOnMouseEntered(e -> {
			pane.setCursor(Cursor.HAND);
			button2.setStyle("-fx-background-color: #414449");
		});

		button3.setOnMouseEntered(e -> {
			pane.setCursor(Cursor.HAND);
			button3.setStyle("-fx-background-color: #414449");
		});

		button4.setOnMouseEntered(e -> {
			pane.setCursor(Cursor.HAND);
			button4.setStyle("-fx-background-color: #414449");
		});

		pane.getChildren().addAll(title, text0, text00, text1, field1, text2, field2, text3, field3, text4, field4, button1, text5, field5, button2, text000, text6, field6, button3, text0000, text7, field7, button4);

		return pane;
	}
}
