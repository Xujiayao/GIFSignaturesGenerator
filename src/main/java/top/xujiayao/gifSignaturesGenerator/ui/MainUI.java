package top.xujiayao.gifSignaturesGenerator.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import top.xujiayao.gifSignaturesGenerator.Main;
import top.xujiayao.gifSignaturesGenerator.tools.Avatar;
import top.xujiayao.gifSignaturesGenerator.tools.GenerateGIF;
import top.xujiayao.gifSignaturesGenerator.tools.GeneratePNG;
import top.xujiayao.gifSignaturesGenerator.tools.ImageUpload;
import top.xujiayao.gifSignaturesGenerator.tools.ParseJSON;
import top.xujiayao.gifSignaturesGenerator.tools.Utils;
import top.xujiayao.gifSignaturesGenerator.tools.Variables;

public class MainUI {

	public static ImageView imageView;

	private double xOffset;
	private double yOffset;

	private Pane root;
	private Button nextButton;

	public void start(Stage stage) {
		root = new Pane();
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

		Pane leftPane = new Pane();
		leftPane.setLayoutX(0);
		leftPane.setLayoutY(40);
		leftPane.setPrefSize(180, 560);
		leftPane.setStyle("-fx-background-color: #24292E; -fx-background-radius: 0 20 0 0");
		root.getChildren().add(leftPane);

		imageView = new ImageView(SwingFXUtils.toFXImage(Variables.avatar, null));
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setFitWidth(140);
		imageView.setFitHeight(140);
		imageView.setLayoutX(20);
		imageView.setLayoutY(20);

		Text text2 = new Text(15, 188, Utils.unicodeToString(Main.projectFlyData.loginData[1]));
		text2.setFont(new Font("Microsoft YaHei Bold", 18));
		text2.setTextAlignment(TextAlignment.CENTER);
		text2.setFill(Color.web("#FFF"));
		text2.setWrappingWidth(150);

		Text text3 = new Text(15, 217, "@" + Utils.unicodeToString(Main.projectFlyData.loginData[0]));
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setTextAlignment(TextAlignment.CENTER);
		text3.setFill(Color.web("#5F5D5F"));
		text3.setWrappingWidth(150);

		Text text4 = new Text(15, 240, Utils.unicodeToString(Main.projectFlyData.loginData[3]) + " MEMBER");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setTextAlignment(TextAlignment.CENTER);
		text4.setFill(Color.web("#5F5D5F"));
		text4.setWrappingWidth(150);

		Label label = new Label(Main.projectFlyData.loginData[4].toUpperCase());
		label.setFont(new Font("Microsoft YaHei Bold", 16));
		label.setTextFill(Color.web("#FFF"));
		label.setStyle("-fx-background-color: " + Main.projectFlyData.loginData[5] + "; -fx-border-width: 2 2 2 2; -fx-border-color: #FFF");
		label.setAlignment(Pos.CENTER);
		label.setPrefSize(140, 26);
		label.setLayoutX(20);
		label.setLayoutY(254);

		Text authorText = new Text(15, 543, "By Xujiayao");
		authorText.setFont(new Font("Microsoft YaHei", 12));
		authorText.setFill(Color.web("#FFF"));
		authorText.setTextAlignment(TextAlignment.CENTER);
		authorText.setWrappingWidth(150);

		leftPane.getChildren().addAll(imageView, text2, text3, text4, label, authorText);

		Pane splitPane = new Pane();
		splitPane.setPrefSize(620, 2);
		splitPane.setLayoutX(180);
		splitPane.setLayoutY(558);
		splitPane.setStyle("-fx-background-color: #24292E");
		root.getChildren().add(splitPane);

		Pane bottomPane = new Pane();
		bottomPane.setPrefSize(620, 40);
		bottomPane.setLayoutX(180);
		bottomPane.setLayoutY(560);
		bottomPane.setStyle("-fx-background-color: #FFF");
		root.getChildren().add(bottomPane);

		Button backButton = new Button("上一步");
		backButton.setFont(new Font("Microsoft YaHei", 14));
		backButton.setStyle("-fx-background-color: #24292E");
		backButton.setTextFill(Color.web("#FFF"));
		backButton.setPrefSize(90, 29);
		backButton.setLayoutX(217);
		backButton.setLayoutY(5);

		nextButton = new Button("下一步");
		nextButton.setFont(new Font("Microsoft YaHei", 14));
		nextButton.setStyle("-fx-background-color: #24292E");
		nextButton.setTextFill(Color.web("#FFF"));
		nextButton.setPrefSize(90, 29);
		nextButton.setLayoutX(313);
		nextButton.setLayoutY(5);

		bottomPane.getChildren().addAll(backButton, nextButton);

		ContextMenu contextMenu = new ContextMenu();

		MenuItem menuItem1 = new MenuItem("首选项");
		MenuItem menuItem2 = new MenuItem("检查更新");
		MenuItem menuItem3 = new MenuItem("关于");

		contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);

		root.getChildren().add(Main.panes.pane1());

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

		backButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			backButton.setStyle("-fx-background-color: #414449");
		});

		nextButton.setOnMouseEntered(e -> {
			root.setCursor(Cursor.HAND);
			nextButton.setStyle("-fx-background-color: #414449");
		});

		minimizeButton.setOnAction(e -> stage.setIconified(true));

		backButton.setOnAction(e -> {
			switch (Main.panes.paneShowing) {
				case 1 -> {
					stage.close();
					new LoginUI().start(stage);
				}
				case 2 -> {
					try {
						root.getChildren().remove(4);
						root.getChildren().add(Main.panes.pane1());

						System.gc();
					} catch (Exception e1) {
						Dialogs.showExceptionDialog(e1);
					}
				}
				case 3 -> {
					try {
						root.getChildren().remove(4);
						root.getChildren().add(Main.panes.pane2());

						nextButton.setText("下一步");

						System.gc();
					} catch (Exception e1) {
						Dialogs.showExceptionDialog(e1);
					}
				}
			}
		});

		nextButton.setOnAction(e -> {
			switch (Main.panes.paneShowing) {
				case 1 -> {
					if (!Avatar.success) {
						Dialogs.showErrorDialog("发生错误", "头像必须先处理成功才能继续下一步。");
						return;
					}

					Main.projectFlyData.profileData[0] = Main.panes.field5.getText();

					try {
						root.getChildren().remove(4);
						root.getChildren().add(Main.panes.pane2());

						System.gc();
					} catch (Exception e1) {
						Dialogs.showExceptionDialog(e1);
					}
				}
				case 2 -> generateSignature();
				case 3 -> {
					stage.close();
					Platform.exit();
					System.exit(0);
				}
			}
		});

		closeButton.setOnAction(e -> {
			stage.close();
			Platform.exit();
			System.exit(0);
		});

		root.setOnMouseMoved(e -> {
			if (nextButton.getText().equals("加载中...")) {
				root.setCursor(Cursor.WAIT);
			} else {
				root.setCursor(Cursor.DEFAULT);
			}

			iconView2.setFill(Color.web("#24292E"));
			iconView3.setFill(Color.web("#24292E"));
			iconView4.setFill(Color.web("#24292E"));
			backButton.setStyle("-fx-background-color: #24292E");
			nextButton.setStyle("-fx-background-color: #24292E");
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

	private void generateSignature() {
		Variables.saveConfig();

		nextButton.setText("加载中...");
		nextButton.setDisable(true);

		root.setCursor(Cursor.WAIT);

		new Thread(() -> {
			boolean success = false;
			String message = null;

			try {
				switch (Variables.useStyleProjectFly) {
					case 1 -> success = GeneratePNG.generateProjectFly1();
				}

				if (success) {
					success = GenerateGIF.generate();
				}

				if (success) {
					message = ImageUpload.upload();
				}
			} catch (Exception e) {
				Platform.runLater(() -> Dialogs.showExceptionDialog(e));
			}

			Platform.runLater(() -> {
				nextButton.setText("下一步");
				nextButton.setDisable(false);

				root.setCursor(Cursor.DEFAULT);
			});

			if (message != null) {
				Variables.uploadData = ParseJSON.parseUploadJSON(message.replaceAll("\\\\", ""));
			}

			if (Variables.uploadData != null) {
				Platform.runLater(() -> {
					try {
						root.getChildren().remove(4);
						root.getChildren().add(Main.panes.pane3());

						nextButton.setText("退出");

						System.gc();
					} catch (Exception e) {
						Dialogs.showExceptionDialog(e);
					}
				});
			}
		}).start();
	}
}
