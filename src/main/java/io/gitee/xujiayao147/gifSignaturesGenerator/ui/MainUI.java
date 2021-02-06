package io.gitee.xujiayao147.gifSignaturesGenerator.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.gitee.xujiayao147.gifSignaturesGenerator.Main;
import io.gitee.xujiayao147.gifSignaturesGenerator.tools.Variables;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainUI {

	private double xOffset;
	private double yOffset;

	public void start(Stage stage) {
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		root.setStyle("-fx-background-color: #FFF");

		Scene scene = new Scene(root);
		stage.setX((Variables.screenWidth - 800) / 2);
		stage.setY((Variables.screenHeight - 600) / 2);
		stage.setScene(scene);

		Pane topPane = new Pane();
		topPane.setLayoutX(0);
		topPane.setLayoutY(0);
		topPane.setPrefSize(800, 40);
		topPane.setStyle("-fx-background-color: #FFF");
		root.getChildren().add(topPane);

		FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.GITHUB, "30");
		iconView1.setFill(Color.web("#24292E"));
		iconView1.setLayoutX(7);
		iconView1.setLayoutY(30);

		Text text1 = new Text(43, 28, "GIF签名图生成工具");
		text1.setFont(new Font("Microsoft YaHei", 22));
		text1.setFill(Color.web("#24292E"));

		FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.BARS, "14");
		iconView2.setFill(Color.web("#24292E"));

		Button barsButton = new Button();
		barsButton.setStyle("-fx-background-color: transparent");
		barsButton.setGraphic(iconView2);
		barsButton.setPrefSize(30, 30);
		barsButton.setLayoutX(704);
		barsButton.setLayoutY(2);

		FontAwesomeIconView iconView3 = new FontAwesomeIconView(FontAwesomeIcon.MINUS, "14");
		iconView3.setFill(Color.web("#24292E"));

		Button minimizeButton = new Button();
		minimizeButton.setStyle("-fx-background-color: transparent");
		minimizeButton.setGraphic(iconView3);
		minimizeButton.setPrefSize(30, 30);
		minimizeButton.setLayoutX(736);
		minimizeButton.setLayoutY(3);

		FontAwesomeIconView iconView4 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE, "14");
		iconView4.setFill(Color.web("#24292E"));

		Button closeButton = new Button();
		closeButton.setStyle("-fx-background-color: transparent");
		closeButton.setGraphic(iconView4);
		closeButton.setPrefSize(30, 30);
		closeButton.setLayoutX(768);
		closeButton.setLayoutY(2);

		topPane.getChildren().addAll(iconView1, text1, barsButton, minimizeButton, closeButton);

//		Pane rightPane = new Pane();
//		rightPane.setPrefSize(350, 500);
//		rightPane.setStyle("-fx-background-color: #FFF");
//		root.setRight(rightPane);
//
//
//		FontAwesomeIconView iconView5 = new FontAwesomeIconView(FontAwesomeIcon.CUBE, "30");
//		iconView5.setFill(Color.web("#24292E"));
//		iconView5.setLayoutX(160);
//		iconView5.setLayoutY(130);
//
//		Text text3 = new Text(153, 165, "欢迎");
//		text3.setFont(new Font("Microsoft YaHei", 22));
//		text3.setFill(Color.web("#24292E"));
//
//		FontAwesomeIconView iconView6 = new FontAwesomeIconView(FontAwesomeIcon.CODE, "20");
//		iconView6.setFill(Color.web("#24292E"));
//		iconView6.setLayoutX(59);
//		iconView6.setLayoutY(223);
//
//		comboBox = new ComboBox<>();
//		comboBox.getItems().addAll("projectFLY", "哔哩哔哩");
//		comboBox.setValue(Variables.loginType);
//		comboBox.setStyle("-fx-background-color: #F3F3F3; -fx-border-color: #24292E; -fx-border-width: 0px 0px 2px 0px");
//		comboBox.setPrefSize(200, 25);
//		comboBox.setLayoutX(91);
//		comboBox.setLayoutY(203);
//
//		iconView7 = new FontAwesomeIconView(FontAwesomeIcon.USER, "20");
//		iconView7.setFill(Color.web("#24292E"));
//		iconView7.setLayoutX(62);
//		iconView7.setLayoutY(257);
//
//		usernameField = new TextField(Variables.username);
//		usernameField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
//		usernameField.setPrefSize(200, 25);
//		usernameField.setFont(new Font("Microsoft YaHei", 12));
//		usernameField.setPromptText("用户名 / 邮箱地址");
//		usernameField.setLayoutX(91);
//		usernameField.setLayoutY(238);
//
//		iconView8 = new FontAwesomeIconView(FontAwesomeIcon.KEY, "20");
//		iconView8.setFill(Color.web("#24292E"));
//		iconView8.setLayoutX(60);
//		iconView8.setLayoutY(292);
//
//		passwordField = new PasswordField();
//		passwordField.setText(Variables.password);
//		passwordField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
//		passwordField.setPrefSize(200, 25);
//		passwordField.setFont(new Font("Microsoft YaHei", 12));
//		passwordField.setPromptText("密码");
//		passwordField.setLayoutX(91);
//		passwordField.setLayoutY(273);
//
//		button = new Button("登录");
//		button.setFont(new Font("Microsoft YaHei", 14));
//		button.setStyle("-fx-background-color: #24292E");
//		button.setTextFill(Color.web("#FFF"));
//		button.setPrefSize(90, 30);
//		button.setLayoutX(130);
//		button.setLayoutY(365);
//
//		Text authorText = new Text(142, 481, "By Xujiayao");
//		authorText.setFont(new Font("Microsoft YaHei", 12));
//		authorText.setFill(Color.web("#24292E"));
//
//		rightPane.getChildren().addAll(barsButton, minimizeButton, closeButton, iconView5, text3, iconView6, comboBox, iconView7, usernameField, iconView8, passwordField, button, authorText);
//
		ContextMenu contextMenu = new ContextMenu();

		MenuItem menuItem1 = new MenuItem("首选项");
		MenuItem menuItem2 = new MenuItem("检查更新");
		MenuItem menuItem3 = new MenuItem("关于");

		contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);

//		if (Avatar.downloadAvatar()) {
//			Avatar.processAvatar();
//
//			MainUI.imageView.setImage(new Image("file:" + Variables.avatarFilePath));
//			Avatar.success = true;
//		}
//
//		comboBox.setOnMouseEntered(e -> root.setCursor(Cursor.HAND));
//
//		button.setOnAction(e -> login());
//
//		root.setOnKeyReleased(e -> {
//			if (e.getCode().equals(KeyCode.ENTER))
//				login();
//		});
//
//		button.setOnMouseEntered(e -> {
//			root.setCursor(Cursor.HAND);
//			button.setStyle("-fx-background-color: #414449");
//		});

		menuItem1.setOnAction(e -> Dialogs.showPreferencesDialog());

		menuItem2.setOnAction(e -> {
			Main.update.isManualRequest = true;
			new Thread(Main.update).start();
		});

		menuItem3.setOnAction(e -> Dialogs.showAboutDialog());

		barsButton.setOnAction(e -> {
			if (contextMenu.isShowing()) {
				contextMenu.hide();
			} else {
				contextMenu.show(barsButton, Side.BOTTOM, 0, 10);
			}
		});

		barsButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			iconView2.setFill(Color.web("#1E90FF"));
		});

		minimizeButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			iconView3.setFill(Color.web("#42D400"));
		});

		closeButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			iconView4.setFill(Color.web("#FF0000"));
		});

		minimizeButton.setOnAction(e -> stage.setIconified(true));

		closeButton.setOnAction(e -> {
			stage.close();
			Platform.exit();
			System.exit(0);
		});

		root.setOnMouseMoved(e -> {
			iconView2.setFill(Color.web("#24292E"));
			iconView3.setFill(Color.web("#24292E"));
			iconView4.setFill(Color.web("#24292E"));
		});

		root.setOnMousePressed(e -> {
			xOffset = e.getSceneX();
			yOffset = e.getSceneY();
		});

		root.setOnMouseDragged(e -> {
			stage.setX(e.getScreenX() - xOffset);
			stage.setY(e.getScreenY() - yOffset);
		});

		stage.show();

		System.gc();
	}
}
