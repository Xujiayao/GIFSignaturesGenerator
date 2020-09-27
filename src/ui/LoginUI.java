package ui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import tools.Avatar;
import tools.ParseJSON;
import tools.ProjectFlyAPI;
import tools.Updates;
import tools.Variables;

/**
 * @author Xujiayao
 */
public class LoginUI extends Application {
	
	private double xOffset;
	private double yOffset;
	
	public static Button button;
	public static TextField textField;
	public static PasswordField passwordField;
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		root.setStyle("-fx-background-color: #FFF");
		
		Scene scene = new Scene(root);
		stage.setTitle("PF签名图生成工具");
		
		if (stage.getStyle() != StageStyle.UNDECORATED) {
			stage.initStyle(StageStyle.UNDECORATED);
		}
		
		stage.setX((Variables.screenWidth - 700) / 2);
		stage.setY((Variables.screenHeight - 500) / 2);
		stage.setScene(scene);
		
		Pane leftPane = new Pane();
		leftPane.setPrefSize(350, 500);
		leftPane.setStyle("-fx-background-color: #25292E");
		root.setLeft(leftPane);
		
		FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.GITHUB);
		iconView1.setFill(Color.web("#FFF"));
		iconView1.setSize("75");
		iconView1.setLayoutX(143);
		iconView1.setLayoutY(243);
		
		Text text1 = new Text("PF签名图生成工具");
		text1.setFont(new Font("Microsoft YaHei", 24));
		text1.setFill(Color.web("#FFF"));
		text1.setLayoutX(77);
		text1.setLayoutY(289);
		
		Text text2 = new Text("GitHub开源项目");
		text2.setFont(new Font("Microsoft YaHei", 18));
		text2.setFill(Color.web("#FFF"));
		text2.setLayoutX(108);
		text2.setLayoutY(314);
		
		leftPane.getChildren().addAll(iconView1, text1, text2);
		
		Pane rightPane = new Pane();
		rightPane.setPrefSize(350, 500);
		rightPane.setStyle("-fx-background-color: #FFF");
		root.setRight(rightPane);
		
		FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.BARS);
		iconView2.setFill(Color.web("#25292E"));
		iconView2.setSize("14");
		
		Button barsButton = new Button();
		barsButton.setStyle("-fx-background-color: transparent");
		barsButton.setGraphic(iconView2);
		barsButton.setPrefSize(30, 30);
		barsButton.setLayoutX(286);
		barsButton.setLayoutY(2);
		
		FontAwesomeIconView iconView3 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
		iconView3.setFill(Color.web("#25292E"));
		iconView3.setSize("15");
		
		Button closeButton = new Button();
		closeButton.setStyle("-fx-background-color: transparent");
		closeButton.setGraphic(iconView3);
		closeButton.setPrefSize(30, 30);
		closeButton.setLayoutX(318);
		closeButton.setLayoutY(2);
		
		FontAwesomeIconView iconView4 = new FontAwesomeIconView(FontAwesomeIcon.PLANE);
		iconView4.setFill(Color.web("#25292E"));
		iconView4.setSize("35");
		iconView4.setLayoutX(161);
		iconView4.setLayoutY(143);
		
		Text text3 = new Text("登录");
		text3.setFont(new Font("Microsoft YaHei", 22));
		text3.setFill(Color.web("#25292E"));
		text3.setLayoutX(153);
		text3.setLayoutY(172);
		
		FontAwesomeIconView iconView5 = new FontAwesomeIconView(FontAwesomeIcon.USER);
		iconView5.setFill(Color.web("#25292E"));
		iconView5.setSize("20");
		iconView5.setLayoutX(60);
		iconView5.setLayoutY(234);
		
		FontAwesomeIconView iconView6 = new FontAwesomeIconView(FontAwesomeIcon.KEY);
		iconView6.setFill(Color.web("#25292E"));
		iconView6.setSize("20");
		iconView6.setLayoutX(58);
		iconView6.setLayoutY(269);
		
		textField = new TextField(Variables.username);
		textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		textField.setPrefSize(200, 25);
		textField.setFont(new Font("Microsoft YaHei", 12));
		textField.setPromptText("用户名 / 邮箱地址");
		textField.setLayoutX(92);
		textField.setLayoutY(215);
		
		passwordField = new PasswordField();
		passwordField.setText(Variables.password);
		passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		passwordField.setPrefSize(200, 25);
		passwordField.setFont(new Font("Microsoft YaHei", 12));
		passwordField.setPromptText("密码");
		passwordField.setLayoutX(92);
		passwordField.setLayoutY(250);
		
		button = new Button("登录");
		button.setFont(new Font("Microsoft YaHei", 14));
		button.setStyle("-fx-background-color: #25292E");
		button.setTextFill(Color.web("#FFF"));
		button.setPrefSize(90, 30);
		button.setLayoutX(130);
		button.setLayoutY(358);
		
		Text text4 = new Text("By Xujiayao");
		text4.setFont(new Font("Microsoft YaHei", 12));
		text4.setFill(Color.web("#25292E"));
		text4.setLayoutX(142);
		text4.setLayoutY(483);
		
		rightPane.getChildren().addAll(iconView2, iconView3, iconView4, iconView5, iconView6, button, text3, text4, textField, passwordField, barsButton, closeButton);
		
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem menuItem1 = new MenuItem("首选项");
		MenuItem menuItem2 = new MenuItem("检查更新                ");
		MenuItem menuItem3 = new MenuItem("关于");
		
		contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);
		
		if (Variables.language.equals("English")) {
			stage.setTitle("PF Signatures Generator");
			
			text1.setText("PF Signatures Generator");
			text1.setLayoutX(36);
			
			text2.setText("Open Source on GitHub");
			text2.setLayoutX(72);
			
			text3.setText("Login");
			text3.setLayoutX(146);
			
			textField.setPromptText("Username / Email Address");
			passwordField.setPromptText("Password");
			
			button.setText("Login");
			
			menuItem1.setText("Preferences");
			menuItem2.setText("Check for Updates");
			menuItem3.setText("About");
		}
		
		menuItem1.setOnAction(e -> {
			Dialogs.showPreferencesDialog();
		});
		
		menuItem2.setOnAction(e -> {
			Updates.start(false);
		});
		
		menuItem3.setOnAction(e -> {
			Dialogs.showAboutDialog();
		});
		
		barsButton.setOnAction(e -> {
			if (contextMenu.isShowing()) {
				contextMenu.hide();
			} else {
				contextMenu.show(barsButton, Side.BOTTOM, -70, 10);
			}
		});
		
		button.setOnAction(e -> {
			if (!textField.getText().equals("") && !passwordField.getText().equals("")) {
				if (Variables.language.equals("English")) {
					button.setText("Loading...");
				} else {
					button.setText("加载中...");
				}
				
				button.setDisable(true);
				
				root.setCursor(Cursor.WAIT);
				
				iconView5.setFill(Color.web("#25292E"));
				textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
				
				iconView6.setFill(Color.web("#25292E"));
				passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
				
				new Thread(new LoginThread()).start();
			} else {
				if (textField.getText().equals("")) {
					iconView5.setFill(Color.web("#FF0000"));
					textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-inner-color: #25292E");
				} else {
					iconView5.setFill(Color.web("#25292E"));
					textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
				}
				
				if (passwordField.getText().equals("")) {
					iconView6.setFill(Color.web("#FF0000"));
					passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-inner-color: #25292E");
				} else {
					iconView6.setFill(Color.web("#25292E"));
					passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
				}
			}
		});
		
		root.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				if (!textField.getText().equals("") && !passwordField.getText().equals("")) {
					if (!button.isDisable()) {
						if (Variables.language.equals("English")) {
							button.setText("Loading...");
						} else {
							button.setText("加载中...");
						}
						
						button.setDisable(true);
						
						root.setCursor(Cursor.WAIT);
						
						iconView5.setFill(Color.web("#25292E"));
						textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
						
						iconView6.setFill(Color.web("#25292E"));
						passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
						
						new Thread(new LoginThread()).start();
					}
				} else {
					if (textField.getText().equals("")) {
						iconView5.setFill(Color.web("#FF0000"));
						textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-inner-color: #25292E");
					} else {
						iconView5.setFill(Color.web("#25292E"));
						textField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
					}
					
					if (passwordField.getText().equals("")) {
						iconView6.setFill(Color.web("#FF0000"));
						passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-inner-color: #25292E");
					} else {
						iconView6.setFill(Color.web("#25292E"));
						passwordField.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
					}
				}
			}
		});
		
		closeButton.setOnAction(e -> {
			stage.close();
			System.exit(0);
		});
		
		root.setOnMouseMoved(e -> {
			if (!(button.getText().equals("Loading...") || button.getText().equals("加载中..."))) {
				root.setCursor(Cursor.DEFAULT);
			}
			
			iconView2.setFill(Color.web("#25292E"));
			iconView3.setFill(Color.web("#25292E"));
			button.setStyle("-fx-background-color: #25292E");
		});
		
		button.setOnMouseEntered(e -> {
			if (!(button.getText().equals("Loading...") || button.getText().equals("加载中..."))) {
				root.setCursor(Cursor.HAND);
			}
			
			button.setStyle("-fx-background-color: #414449");
		});
		
		barsButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			iconView2.setFill(Color.web("#42D400"));
		});
		
		closeButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			iconView3.setFill(Color.web("#FF0000"));
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

class LoginThread implements Runnable {
	@Override
	public void run() {
		String data = ProjectFlyAPI.login(LoginUI.textField.getText(), LoginUI.passwordField.getText());
		
		Platform.runLater(() -> {
			if (Variables.language.equals("English")) {
				LoginUI.button.setText("Login");
			} else {
				LoginUI.button.setText("登录");
			}
			
			LoginUI.button.setDisable(false);
		});
		
		if (data != null) {
			ParseJSON.parseLoginJSON(data);
			
			Variables.username = LoginUI.textField.getText();
			Variables.password = LoginUI.passwordField.getText();			
			
			Variables.saveAccount(LoginUI.textField.getText(), LoginUI.passwordField.getText());
			
			Platform.runLater(() -> {
				try {
					Stage stage = (Stage)LoginUI.button.getScene().getWindow();
				    stage.close();
				    
					new MainUI().start(stage);
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
			});
			
			boolean success = Avatar.downloadAvatar();
			
			if (success) {
				Avatar.processAvatar();
				
				Platform.runLater(() -> {
					MainUI.imageView.setImage(new Image("file:" + Variables.avatarFilePath));
				});
			} else {
				try {
					BufferedImage image = ImageIO.read(new File(Main.class.getResource("/images/no-profile-image.png").toURI()));
					
					File file = new File(Variables.dataFolder + "/avatar.png");
					ImageIO.write(image, "png", file);
					
					Variables.avatarFilePath = Variables.dataFolder + "/avatar.png";
				} catch (Exception e) {
					Platform.runLater(() -> {
						Dialogs.showExceptionDialog(e);
					});
				}
			}
		}
	}
}