package ui;

import java.awt.TrayIcon;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.Avatar;
import tools.ParseJSON;
import tools.ProjectFlyAPI;
import tools.SystemTray;
import tools.Update;
import tools.Variables;

/**
 * @author Xujiayao
 */
public class MainUI extends Application {
	
	private double xOffset;
	private double yOffset;
	
	public static ImageView imageView;
	
	public static Button nextButton;
	
	public static Pane root;
	
	@Override
	public void start(Stage stage) throws Exception {
		root = new Pane();
		root.setPrefSize(800, 600);
		root.setStyle("-fx-background-color: #FFF");
		
		Scene scene = new Scene(root);
		stage.setTitle("PF签名图生成工具");

		if (stage.getStyle() != StageStyle.UNDECORATED) {
			stage.initStyle(StageStyle.UNDECORATED);
		}
		
		stage.setX((Variables.screenWidth - 800) / 2);
		stage.setY((Variables.screenHeight - 600) / 2);
		stage.setScene(scene);
		
		Pane topPane = new Pane();
		topPane.setLayoutX(0);
		topPane.setLayoutY(0);
		topPane.setPrefSize(800, 40);
		topPane.setStyle("-fx-background-color: #FFF");
		root.getChildren().add(topPane);
		
		FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.GITHUB);
		iconView1.setFill(Color.web("#25292E"));
		iconView1.setSize("30");
		iconView1.setLayoutX(7);
		iconView1.setLayoutY(31);
		
		Text text1 = new Text("PF签名图生成工具");
		text1.setFont(new Font("Microsoft YaHei", 22));
		text1.setFill(Color.web("#25292E"));
		text1.setLayoutX(43);
		text1.setLayoutY(28);

		FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.BARS);
		iconView2.setFill(Color.web("#25292E"));
		iconView2.setSize("14");
		
		Button barsButton = new Button();
		barsButton.setStyle("-fx-background-color: transparent");
		barsButton.setGraphic(iconView2);
		barsButton.setPrefSize(30, 30);
		barsButton.setLayoutX(736);
		barsButton.setLayoutY(2);
		
		FontAwesomeIconView iconView3 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
		iconView3.setFill(Color.web("#25292E"));
		iconView3.setSize("15");
		
		Button closeButton = new Button();
		closeButton.setStyle("-fx-background-color: transparent");
		closeButton.setGraphic(iconView3);
		closeButton.setPrefSize(30, 30);
		closeButton.setLayoutX(768);
		closeButton.setLayoutY(2);
		
		topPane.getChildren().addAll(iconView1, text1, barsButton, closeButton);
		
		Pane leftPane = new Pane();
		leftPane.setPrefSize(180, 560);
		leftPane.setLayoutX(0);
		leftPane.setLayoutY(40);
		leftPane.setStyle("-fx-background-color: #25292e; -fx-background-radius: 0 20 0 0");
		root.getChildren().add(leftPane);
		
		imageView = new ImageView(new Image(MainUI.class.getResource("/images/no-profile-image.png").toURI().toString()));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(140);
		imageView.setFitHeight(140);
		imageView.setLayoutX(20);
		imageView.setLayoutY(20);
		
		Text text2 = new Text(ProjectFlyAPI.unicodeToString(Variables.loginData[1]));
		text2.setFont(new Font("Microsoft YaHei Bold", 18));
		text2.setTextAlignment(TextAlignment.CENTER);
		text2.setFill(Color.web("#FFF"));
		text2.setWrappingWidth(150);
		text2.setLayoutX(15);
		text2.setLayoutY(188);
		
		Text text3 = new Text("@" + Variables.loginData[0]);
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setTextAlignment(TextAlignment.CENTER);
		text3.setFill(Color.web("#5F5D5F"));
		text3.setWrappingWidth(150);
		text3.setLayoutX(15);
		text3.setLayoutY(217);
		
		Text text4 = new Text(Variables.loginData[3] + " MEMBER");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setTextAlignment(TextAlignment.CENTER);
		text4.setFill(Color.web("#5F5D5F"));
		text4.setWrappingWidth(150);
		text4.setLayoutX(15);
		text4.setLayoutY(240);
		
		Label label = new Label(Variables.loginData[4].toUpperCase());
		label.setFont(new Font("Microsoft YaHei Bold", 16));
		label.setTextFill(Color.web("#FFF"));
		label.setStyle("-fx-background-color: " + Variables.loginData[5] + "; -fx-border-width: 1 1 1 1; -fx-border-color: #FFF");
		label.setAlignment(Pos.CENTER);
		label.setPrefSize(140, 26);
		label.setLayoutX(20);
		label.setLayoutY(254);
		
		Text text5 = new Text("By Xujiayao");
		text5.setFont(new Font("Microsoft YaHei", 12));
		text5.setTextAlignment(TextAlignment.CENTER);
		text5.setFill(Color.web("#FFF"));
		text5.setWrappingWidth(150);
		text5.setLayoutX(15);
		text5.setLayoutY(543);
		
		leftPane.getChildren().addAll(imageView, text2, text3, text4, label, text5);
		
		Pane splitPane = new Pane();
		splitPane.setPrefSize(620, 2);
		splitPane.setLayoutX(180);
		splitPane.setLayoutY(558);
		splitPane.setStyle("-fx-background-color: #25292E");
		root.getChildren().add(splitPane);
		
		Pane bottomPane = new Pane();
		bottomPane.setPrefSize(620, 40);
		bottomPane.setLayoutX(180);
		bottomPane.setLayoutY(560);
		bottomPane.setStyle("-fx-background-color: #FFF");
		root.getChildren().add(bottomPane);
		
		Button backButton = new Button("上一步");
		backButton.setFont(new Font("Microsoft YaHei", 14));
		backButton.setStyle("-fx-background-color: #25292E");
		backButton.setTextFill(Color.web("#FFF"));
		backButton.setPrefSize(90, 29);
		backButton.setLayoutX(217);
		backButton.setLayoutY(5);
		
		nextButton = new Button("下一步");
		nextButton.setFont(new Font("Microsoft YaHei", 14));
		nextButton.setStyle("-fx-background-color: #25292E");
		nextButton.setTextFill(Color.web("#FFF"));
		nextButton.setPrefSize(90, 29);
		nextButton.setLayoutX(313);
		nextButton.setLayoutY(5);
		
		bottomPane.getChildren().addAll(backButton, nextButton);
		
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem menuItem1 = new MenuItem("首选项");
		MenuItem menuItem2 = new MenuItem("检查更新                ");
		MenuItem menuItem3 = new MenuItem("关于");
		
		contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);
		
		root.getChildren().add(Panes.pane1());
		
		if (Variables.language.equals("English")) {
			stage.setTitle("PF Signatures Generator");
			
			text1.setText("PF Signatures Generator");
			
			menuItem1.setText("Preferences");
			menuItem2.setText("Check for Updates");
			menuItem3.setText("About");
			
			backButton.setText("Back");
			nextButton.setText("Next");
		}
		
		backButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			backButton.setStyle("-fx-background-color: #414449");
		});
		
		nextButton.setOnMouseEntered(e -> {
			if (!(nextButton.getText().equals("Loading...") || nextButton.getText().equals("加载中..."))) {
				root.setCursor(Cursor.HAND);
			}
			
			nextButton.setStyle("-fx-background-color: #414449");
		});
		
		backButton.setOnAction(e -> {
			if (Panes.paneShowing == 1) {
				try {
					stage.close();
					
					new LoginUI().start(stage);
				} catch (Exception e3) {
					Dialogs.showExceptionDialog(e3);
				}
			} else if (Panes.paneShowing == 2) {
				root.getChildren().remove(4);
				root.getChildren().add(Panes.pane1());
			}
		});
		
		nextButton.setOnAction(e -> {
			if (Panes.paneShowing == 1) {
				if (Variables.language.equals("English")) {
					nextButton.setText("Loading...");
				} else {
					nextButton.setText("加载中...");
				}
				
				nextButton.setDisable(true);
				
				root.setCursor(Cursor.WAIT);
				
				new Thread(new ProfilesThread()).start();
			} else if (Panes.paneShowing == 2) {
				
			}
		});
		
		menuItem1.setOnAction(e -> {
			Dialogs.showPreferencesDialog();
		});
		
		menuItem2.setOnAction(e -> {
			Update.start(false);
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
		
		closeButton.setOnAction(e -> {
			stage.close();
			System.exit(0);
		});
		
		root.setOnMouseMoved(e -> {
			if (!(nextButton.getText().equals("Loading...") || nextButton.getText().equals("加载中..."))) {
				root.setCursor(Cursor.DEFAULT);
			} else {
				root.setCursor(Cursor.WAIT);
			}
			
			iconView2.setFill(Color.web("#25292E"));
			iconView3.setFill(Color.web("#25292E"));
			backButton.setStyle("-fx-background-color: #25292E");
			nextButton.setStyle("-fx-background-color: #25292E");
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

class ProfilesThread implements Runnable {
	@Override
	public void run() {
		if (!Avatar.success) {
			Platform.runLater(() -> {
				if (Variables.language.equals("English")) {
					Dialogs.showErrorDialog("Please wait for the avatar to be successfully processed before proceeding to the next step.", true);
					MainUI.nextButton.setText("Next");
				} else {
					Dialogs.showErrorDialog("请在继续下一步之前先等头像处理成功。", false);
					MainUI.nextButton.setText("下一步");
				}
				
				MainUI.nextButton.setDisable(false);
			});
		} else {
			String data = ProjectFlyAPI.getProfiles(0);
			
			if (data != null) {
				boolean success = ParseJSON.parseProfileJSON(data);
				
				if (success) {
					if (Variables.language.equals("English")) {
						SystemTray.trayIcon.displayMessage("PF Signatures Generator", "Parse successfully", TrayIcon.MessageType.NONE);
					} else {
						SystemTray.trayIcon.displayMessage("PF签名图生成工具", "解析成功", TrayIcon.MessageType.NONE);
					}
					
					Platform.runLater(() -> {
						if (Variables.language.equals("English")) {
							MainUI.nextButton.setText("Next");
						} else {
							MainUI.nextButton.setText("下一步");
						}
						
						MainUI.nextButton.setDisable(false);
						
						MainUI.root.setCursor(Cursor.DEFAULT);
						
						MainUI.root.getChildren().remove(4);
						MainUI.root.getChildren().add(Panes.pane2());
					});
				//	data = ProjectFlyAPI.getProfiles(1);
					
				//	if (data != null) {
				//		success = ParseJSON.parseProfileJSON(data);
						
				//		if (success) {
							
				//		}
				//	}
				}
			}
		}
	}
}