package top.xujiayao.gif_signatures_generator.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import top.xujiayao.gif_signatures_generator.Main;
import top.xujiayao.gif_signatures_generator.tools.Avatar;
import top.xujiayao.gif_signatures_generator.tools.ParseJSON;
import top.xujiayao.gif_signatures_generator.tools.ProjectFlyAPI;
import top.xujiayao.gif_signatures_generator.tools.Variables;

import java.awt.TrayIcon;
import java.awt.image.BufferedImage;

/**
 * @author Xujiayao
 */
public class LoginUI {

	private final int[] getProfileSuccessCount = new int[1];
	public Text text2;
	private double xOffset;
	private double yOffset;
	private BorderPane root;
	private ComboBox<String> comboBox;
	private FontAwesomeIconView iconView7;
	private FontAwesomeIconView iconView8;
	private TextField usernameField;
	private PasswordField passwordField;
	private Button button;

	public void start(Stage stage) {
		root = new BorderPane();
		root.setPrefSize(700, 500);

		Scene scene = new Scene(root);
		stage.setTitle("GIF签名图生成工具");
		stage.setX((Variables.screenWidth - 700) / 2);
		stage.setY((Variables.screenHeight - 500) / 2);
		stage.setScene(scene);

		Pane leftPane = new Pane();
		leftPane.setPrefSize(350, 500);
		leftPane.setStyle("-fx-background-color: #24292E");
		root.setLeft(leftPane);

		FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.GITHUB, "75");
		iconView1.setFill(Color.web("#FFF"));
		iconView1.setLayoutX(143);
		iconView1.setLayoutY(256);

		Text text1 = new Text(72, 301, "GIF签名图生成工具");
		text1.setFont(new Font(Variables.FONTS[0], 24));
		text1.setFill(Color.web("#FFF"));

		text2 = new Text(20, 457, Variables.displayHitokotoData);
		if ((Variables.hitokotoData != null) && (Variables.hitokotoData[0].length() > 21)) {
			text2.setY(438);
		}
		text2.setFont(new Font(Variables.FONTS[0], 14));
		text2.setWrappingWidth(310);
		text2.setTextAlignment(TextAlignment.CENTER);
		text2.setFill(Color.web("#FFF"));

		leftPane.getChildren().addAll(iconView1, text1, text2);

		Pane rightPane = new Pane();
		rightPane.setPrefSize(350, 500);
		rightPane.setStyle("-fx-background-color: #FFF");
		root.setRight(rightPane);

		FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.BARS, "14");
		iconView2.setFill(Color.web("#24292E"));

		Button barsButton = new Button();
		barsButton.setStyle("-fx-background-color: transparent");
		barsButton.setGraphic(iconView2);
		barsButton.setPrefSize(30, 30);
		barsButton.setLayoutX(254);
		barsButton.setLayoutY(2);

		FontAwesomeIconView iconView3 = new FontAwesomeIconView(FontAwesomeIcon.MINUS, "14");
		iconView3.setFill(Color.web("#24292E"));

		Button minimizeButton = new Button();
		minimizeButton.setStyle("-fx-background-color: transparent");
		minimizeButton.setGraphic(iconView3);
		minimizeButton.setPrefSize(30, 30);
		minimizeButton.setLayoutX(286);
		minimizeButton.setLayoutY(3);

		FontAwesomeIconView iconView4 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE, "14");
		iconView4.setFill(Color.web("#24292E"));

		Button closeButton = new Button();
		closeButton.setStyle("-fx-background-color: transparent");
		closeButton.setGraphic(iconView4);
		closeButton.setPrefSize(30, 30);
		closeButton.setLayoutX(318);
		closeButton.setLayoutY(2);

		FontAwesomeIconView iconView5 = new FontAwesomeIconView(FontAwesomeIcon.CUBE, "30");
		iconView5.setFill(Color.web("#24292E"));
		iconView5.setLayoutX(160);
		iconView5.setLayoutY(130);

		Text text3 = new Text(153, 165, "欢迎");
		text3.setFont(new Font(Variables.FONTS[0], 22));
		text3.setFill(Color.web("#24292E"));

		FontAwesomeIconView iconView6 = new FontAwesomeIconView(FontAwesomeIcon.CODE, "20");
		iconView6.setFill(Color.web("#24292E"));
		iconView6.setLayoutX(59);
		iconView6.setLayoutY(223);

		comboBox = new ComboBox<>();
		comboBox.getItems().addAll("projectFLY", "哔哩哔哩");
		comboBox.setValue(Variables.loginType);
		comboBox.setStyle("-fx-background-color: #F3F3F3; -fx-border-color: #24292E; -fx-border-width: 0px 0px 2px 0px");
		comboBox.setPrefSize(200, 25);
		comboBox.setLayoutX(91);
		comboBox.setLayoutY(203);

		iconView7 = new FontAwesomeIconView(FontAwesomeIcon.USER, "20");
		iconView7.setFill(Color.web("#24292E"));
		iconView7.setLayoutX(62);
		iconView7.setLayoutY(257);

		usernameField = new TextField(Variables.username);
		usernameField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		usernameField.setPrefSize(200, 25);
		usernameField.setFont(new Font(Variables.FONTS[0], 12));
		usernameField.setPromptText("用户名 / 邮箱地址");
		usernameField.setLayoutX(91);
		usernameField.setLayoutY(238);

		iconView8 = new FontAwesomeIconView(FontAwesomeIcon.KEY, "20");
		iconView8.setFill(Color.web("#24292E"));
		iconView8.setLayoutX(60);
		iconView8.setLayoutY(292);

		passwordField = new PasswordField();
		passwordField.setText(Variables.password);
		passwordField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		passwordField.setPrefSize(200, 25);
		passwordField.setFont(new Font(Variables.FONTS[0], 12));
		passwordField.setPromptText("密码");
		passwordField.setLayoutX(91);
		passwordField.setLayoutY(273);

		button = new Button("登录");
		button.setFont(new Font(Variables.FONTS[0], 14));
		button.setStyle("-fx-background-color: #24292E");
		button.setTextFill(Color.web("#FFF"));
		button.setPrefSize(90, 30);
		button.setLayoutX(130);
		button.setLayoutY(365);

		Text authorText = new Text(142, 481, "By Xujiayao");
		authorText.setFont(new Font(Variables.FONTS[0], 12));
		authorText.setFill(Color.web("#24292E"));

		rightPane.getChildren().addAll(barsButton, minimizeButton, closeButton, iconView5, text3, iconView6, comboBox, iconView7, usernameField, iconView8, passwordField, button, authorText);

		ContextMenu contextMenu = new ContextMenu();

		MenuItem menuItem1 = new MenuItem("首选项");
		MenuItem menuItem2 = new MenuItem("检查更新");
		MenuItem menuItem3 = new MenuItem("关于");

		contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);

		comboBox.setOnMouseEntered(e -> root.setCursor(Cursor.HAND));

		button.setOnAction(e -> login());

		root.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				login();
			}
		});

		button.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			button.setStyle("-fx-background-color: #414449");
		});

		menuItem1.setOnAction(e -> Dialogs.showPreferencesDialog());

		menuItem2.setOnAction(e -> {
			Main.update.setManualRequest(true);
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
			if (button.getText().equals("加载中...")) {
				root.setCursor(Cursor.WAIT);
			} else {
				root.setCursor(Cursor.DEFAULT);
			}

			iconView2.setFill(Color.web("#24292E"));
			iconView3.setFill(Color.web("#24292E"));
			iconView4.setFill(Color.web("#24292E"));
			button.setStyle("-fx-background-color: #24292E");
		});

		root.setOnMousePressed(e -> {
			xOffset = e.getSceneX();
			yOffset = e.getSceneY();
		});

		root.setOnMouseDragged(e -> {
			stage.setX(e.getScreenX() - xOffset);
			stage.setY(e.getScreenY() - yOffset);
		});

		button.requestFocus();

		stage.show();

		System.gc();
	}

	private void login() {
		if (usernameField.getText().equals("")) {
			iconView7.setFill(Color.web("#FF0000"));
			usernameField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-inner-color: #24292E");
		} else {
			iconView7.setFill(Color.web("#24292E"));
			usernameField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		}

		if (passwordField.getText().equals("")) {
			iconView8.setFill(Color.web("#FF0000"));
			passwordField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #ff0000; -fx-text-inner-color: #24292E");
		} else {
			iconView8.setFill(Color.web("#24292E"));
			passwordField.setStyle("-fx-background-color: #F3F3F3; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #24292E; -fx-text-inner-color: #24292E");
		}

		if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
			return;
		}

		button.setText("加载中...");
		button.setDisable(true);

		root.setCursor(Cursor.WAIT);

		new Thread(() -> {
			try {
				switch (comboBox.getValue()) {
					case "projectFLY" -> {
						Main.setProjectFlyData(new Variables.ProjectFly());

						Main.getProjectFlyData().setLoginData(ParseJSON.parseLoginJSON(ProjectFlyAPI.login(usernameField.getText(), passwordField.getText())));

						getProfileSuccessCount[0] = 0;

						if (Main.getProjectFlyData().getLoginData().length != 0) {
							new Thread(() -> {
								try {
									Main.getProjectFlyData().setProfileData(ParseJSON.parseProfileJSON(ProjectFlyAPI.getProfile(0)));

									if (Main.getProjectFlyData().getProfileData().length != 0) {
										getProfileSuccessCount[0]++;
										finishLogin();
									} else {
										errorLogin();
									}
								} catch (Exception e) {
									Platform.runLater(() -> Dialogs.showExceptionDialog(e));
								}
							}).start();

							new Thread(() -> {
								try {
									Main.getProjectFlyData().setLogbookData(ParseJSON.parseLogbookJSON(ProjectFlyAPI.getProfile(1)));

									if (Main.getProjectFlyData().getLogbookData().length != 0) {
										getProfileSuccessCount[0]++;
										finishLogin();
									} else {
										errorLogin();
									}
								} catch (Exception e) {
									Platform.runLater(() -> Dialogs.showExceptionDialog(e));
								}
							}).start();

							new Thread(() -> {
								try {
									Main.getProjectFlyData().setPassportData(ParseJSON.parsePassportJSON(ProjectFlyAPI.getProfile(2)));

									if (Main.getProjectFlyData().getPassportData() != null) {
										getProfileSuccessCount[0]++;
										finishLogin();
									} else {
										errorLogin();
									}
								} catch (Exception e) {
									Platform.runLater(() -> Dialogs.showExceptionDialog(e));
								}
							}).start();
						} else {
							errorLogin();
						}
					}
					case "哔哩哔哩" -> {
						errorLogin();

						Main.systemTray.getTrayIcon().displayMessage("GIF签名图生成工具", "哔哩哔哩的还没做喔~", TrayIcon.MessageType.NONE);
					}
				}
			} catch (Exception e) {
				Platform.runLater(() -> Dialogs.showExceptionDialog(e));
			}
		}).start();
	}

	private void finishLogin() {
		if (getProfileSuccessCount[0] != 3) {
			return;
		}

		try {
			if (Main.getProjectFlyData().getProfileData() != null &&
				  Main.getProjectFlyData().getLogbookData() != null &&
				  Main.getProjectFlyData().getPassportData() != null) {
				Platform.runLater(() -> {
					button.setText("登录");
					button.setDisable(false);

					root.setCursor(Cursor.DEFAULT);
				});

				Variables.loginType = comboBox.getValue();
				Variables.username = usernameField.getText();
				Variables.password = passwordField.getText();

				Variables.saveConfig();

				Main.systemTray.getTrayIcon().displayMessage("GIF签名图生成工具", "登录成功", TrayIcon.MessageType.NONE);

				Platform.runLater(() -> {
					Main.setPanes(new Panes());

					Main.getStage().close();

					if (Main.getMainUI() == null) {
						Main.setMainUI(new MainUI());
					}

					Main.getMainUI().start(Main.getStage());
				});

				BufferedImage avatar = Avatar.processAvatar(Avatar.downloadAvatar());

				if (avatar != null) {
					Variables.avatar = avatar;

					MainUI.imageView.setImage(SwingFXUtils.toFXImage(Variables.avatar, null));
					Avatar.setSuccess(true);
				}
			}
		} catch (Exception e) {
			Platform.runLater(() -> Dialogs.showExceptionDialog(e));
		}
	}

	private void errorLogin() {
		Platform.runLater(() -> {
			button.setText("登录");
			button.setDisable(false);

			root.setCursor(Cursor.DEFAULT);
		});
	}
}
