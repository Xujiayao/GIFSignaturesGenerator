package ui;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tools.Update;
import tools.Variables;

/**
 * @author Xujiayao
 */
public class Dialogs {
	
	public static ProgressBar bar;
	public static Text text;
	public static Dialog<ButtonType> dialog;
	
	public static void showDownloadingDialog() {
		dialog = new Dialog<>();
		dialog.setTitle("下载更新");
		
		Pane pane = new Pane();
		pane.setPrefWidth(400);
		pane.setPrefHeight(55);
		
		text = new Text("连接中 (0%)");
		text.setFont(new Font("Microsoft YaHei", 14));
		text.setFill(Color.web("#323232"));
		text.setLayoutX(10);
		text.setLayoutY(22);
		
		bar = new ProgressBar(0);
		bar.setPrefWidth(380);
		bar.setPrefHeight(25);
		bar.setLayoutX(10);
		bar.setLayoutY(40);
		bar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		
		pane.getChildren().addAll(text, bar);
		
		if (Variables.language.equals("English")) {
			dialog.setTitle("Download Update");
			
			text.setText("Connecting (0%)");
		}
		
		dialog.getDialogPane().setContent(pane);
		
		dialog.show();
	}
	
	public static void showMessageDialog(String string, String title, boolean english) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(string);
		
		alert.showAndWait();
	}

	public static boolean showConfirmDialog(String string, String title, String header, boolean english) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(string);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
		   return true;
		}
		
		return false;
	}
	
	public static void showPreferencesDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("首选项");
		
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		Tab tab1 = new Tab("外观");
		tab1.setClosable(false);
		
		Tab tab2 = new Tab("更新");
		tab2.setClosable(false);
		
		TabPane tabPane = new TabPane(tab1, tab2);
		tabPane.setPrefSize(400, 250);
		
		Pane pane1 = new Pane();
		Pane pane2 = new Pane();
		
		Text text1 = new Text("界面语言");
		text1.setFont(new Font("Microsoft YaHei", 14));
		text1.setFill(Color.web("#323232"));
		text1.setLayoutX(0);
		text1.setLayoutY(22);

		Separator separator1 = new Separator(Orientation.HORIZONTAL);
		separator1.setPrefWidth(315);
		separator1.setLayoutX(65);
		separator1.setLayoutY(17);
		
		ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("中文");
		rb1.setToggleGroup(group);
		rb1.setLayoutX(0);
		rb1.setLayoutY(42);
		rb1.setSelected(true);
		
		RadioButton rb2 = new RadioButton("English");
		rb2.setToggleGroup(group);
		rb2.setLayoutX(0);
		rb2.setLayoutY(62);
		
		Text text2 = new Text("缓存");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setFill(Color.web("#323232"));
		text2.setLayoutX(0);
		text2.setLayoutY(112);

		Separator separator2 = new Separator(Orientation.HORIZONTAL);
		separator2.setPrefWidth(340);
		separator2.setLayoutX(40);
		separator2.setLayoutY(107);
		
		Button button1 = new Button("清理缓存");
		button1.setFont(new Font("Microsoft YaHei", 12));
		button1.setPrefSize(110, 25);
		button1.setLayoutX(0);
		button1.setLayoutY(132);
		
		Text text3 = new Text("PF签名图生成工具可以自动检查其更新版本。检查将在后台执行，并且只有在有新版本可用时才会通知您。");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setFill(Color.web("#323232"));
		text3.setWrappingWidth(360);
		text3.setLayoutX(0);
		text3.setLayoutY(22);
		
		Text text4 = new Text("检查频率：");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setFill(Color.web("#323232"));
		text4.setLayoutX(0);
		text4.setLayoutY(112);
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("每次启动时", "从不");
		choiceBox.setValue("每次启动时");
		choiceBox.setPrefSize(140, 20);
		choiceBox.setLayoutX(140);
		choiceBox.setLayoutY(97);
		
		Button button2 = new Button("现在检查");
		button2.setFont(new Font("Microsoft YaHei", 12));
		button2.setPrefSize(110, 25);
		button2.setLayoutX(0);
		button2.setLayoutY(132);
		
		if (Variables.language.equals("English")) {
			dialog.setTitle("Preferences");
			
			tab1.setText("Appearance");
			tab2.setText("Updates");
			
			text1.setText("Language");
			
			separator1.setPrefWidth(315);
			separator1.setLayoutX(85);
			
			rb2.setSelected(true);

			text2.setText("Caches");
			
			separator2.setPrefWidth(335);
			separator2.setLayoutX(65);
			
			button1.setText("Clear caches");
			
			text3.setText("PF Signatures Generator can check for updated versions of itself automatically. The check will be performed in the background and you will only be notified if a new version is available.");
			text4.setText("Check for updates: ");
			
			choiceBox.getItems().clear();
			choiceBox.getItems().addAll("On every start", "Never");
			choiceBox.setValue("On every start");
			
			if (Variables.checkUpdates == false) {
				choiceBox.setValue("Never");
			}
			
			button2.setText("Check now");
		} else {
			if (Variables.checkUpdates == false) {
				choiceBox.setValue("从不");
			}
		}
		
		pane1.getChildren().addAll(text1, separator1, rb1, rb2, text2, separator2, button1);
		pane2.getChildren().addAll(text3, text4, choiceBox, button2);

		tab1.setContent(pane1);
		tab2.setContent(pane2);
		
		dialog.getDialogPane().setContent(tabPane);
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean success = Variables.clearCaches();
				
				if (success) {
					if (Variables.language.equals("English")) {
						showMessageDialog("The caches have been cleared.", "Clear Caches", true);
					} else {
						showMessageDialog("缓存已经清理完成。", "清理缓存", false);
					}
				} else {
					if (Variables.language.equals("English")) {
						showErrorDialog("The caches cannot be cleared.", true);
					} else {
						showErrorDialog("缓存清理失败。", false);
					}
				}
			}
		});
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Update.start(false);
			}
		});
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		result.ifPresent(usernamePassword -> {
			if (result.get().getButtonData() == ButtonData.OK_DONE) {
				String language;
		    	
		    	if (rb1.isSelected()) {
		    		Variables.saveLanguage = "中文";
					language = "中文";
				} else {
					Variables.saveLanguage = "English";
					language = "English";
				}
		    	
		    	if (choiceBox.getValue().equals("从不") || choiceBox.getValue().equals("Never")) {
		    		Variables.saveCheckUpdates = false;
				} else {
					Variables.saveCheckUpdates = true;
				}
		    	
		    	Variables.saveVariables(false);
		    	
		    	File file = null;
		    	
				try {
					file = new File(System.getProperty("user.dir") + "/PFSignaturesGenerator.exe");
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
				
		    	if (file.exists()) {
		    		boolean confirmRestart;
			    	
			    	if (language.equals("English")) {
						confirmRestart = Dialogs.showConfirmDialog("Do you want to restart the application now to apply the new settings?", "Confirm", null, true);
					} else {
						confirmRestart = Dialogs.showConfirmDialog("您是否想要现在重新启动应用程序以应用新设置？", "确认", null, false);
					}
			    	
			    	if (confirmRestart) {
						try {
							Desktop.getDesktop().open(file);
							
							System.exit(0);
						} catch (Exception e) {
							Dialogs.showExceptionDialog(e);
						}
					}
				} else {
					if (language.equals("English")) {
						Dialogs.showErrorDialog("Cannot restart the application, you have to restart the application manually to apply the new settings.", true);
					} else {
						Dialogs.showErrorDialog("无法重启应用程序，您需要手动重启应用程序以应用新设置。", false);
					}
				}
			}
		});
	}
	
	public static void showAboutDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("关于");
		alert.setHeaderText("关于PF签名图生成工具");
		
		Pane pane = new Pane();
		pane.setPrefSize(400, 250);
		
		Text text1 = new Text("PF签名图生成工具 " + Variables.version);
		text1.setFont(new Font("Microsoft YaHei", 14));
		text1.setFill(Color.web("#323232"));
		text1.setLayoutX(10);
		text1.setLayoutY(22);
		
		Text text2 = new Text("By Xujiayao");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setFill(Color.web("#323232"));
		text2.setTextAlignment(TextAlignment.RIGHT);
		text2.setWrappingWidth(190);
		text2.setLayoutX(200);
		text2.setLayoutY(22);
		
		Separator separator1 = new Separator(Orientation.HORIZONTAL);
		separator1.setPrefWidth(380);
		separator1.setLayoutX(10);
		separator1.setLayoutY(42);
		
		Text text3 = new Text("用户输入的变量将被存储在 [%AppData%/Java Projects] 文件夹中。\r\n\r\n" + 
				"已存储的变量将自动被填充，您可以在首选项删除这些缓存。");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setFill(Color.web("#323232"));
		text3.setLayoutX(10);
		text3.setLayoutY(62);
		text3.setWrappingWidth(380);
		
		Separator separator2 = new Separator(Orientation.HORIZONTAL);
		separator2.setPrefWidth(380);
		separator2.setLayoutX(10);
		separator2.setLayoutY(152);
		
		Text text4 = new Text("此应用程序只兼容 Windows 系统。");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setFill(Color.web("#323232"));
		text4.setLayoutX(10);
		text4.setLayoutY(172);
		
		Separator separator3 = new Separator(Orientation.HORIZONTAL);
		separator3.setPrefWidth(380);
		separator3.setLayoutX(10);
		separator3.setLayoutY(192);
		
		Text text5 = new Text("这是一个开源项目。");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setFill(Color.web("#323232"));
		text5.setLayoutX(10);
		text5.setLayoutY(212);
		
		Hyperlink link1 = new Hyperlink("GitHub");
		link1.setFont(new Font("Microsoft YaHei", 14));
		link1.setLayoutX(230);
		link1.setLayoutY(193);
		
		Text text6 = new Text("欢迎访问我的博客！");
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setFill(Color.web("#323232"));
		text6.setLayoutX(10);
		text6.setLayoutY(232);
		
		Hyperlink link2 = new Hyperlink("Xujiayao");
		link2.setFont(new Font("Microsoft YaHei", 14));
		link2.setLayoutX(230);
		link2.setLayoutY(213);
		
		if (Variables.language.equals("English")) {
			alert.setTitle("About");
			alert.setHeaderText("About PF Signatures Generator");
			
			text1.setText("PF Signatures Generator " + Variables.version);
			text3.setText("Variables user entered will be stored in folder [%AppData%/Java Projects].\r\n\r\n" + 
					"Stored variables will be filled in automatically, you can delete these caches in Preferences.");
			text4.setText("This application is only compatible with Windows.");
			text5.setText("This is an open source project.");
			text6.setText("Welcome to my blog!");
		}
		
		pane.getChildren().addAll(text1, text2, separator1, text3, separator2, text4, separator3, text5, link1, text6, link2);
		
		alert.getDialogPane().setContent(pane);
		
		link1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/Xujiayao147/PFSignaturesGenerator"));
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
			}
		});
		
		link2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URI("https://xujiayao147.gitee.io/"));
				} catch (Exception e) {
					Dialogs.showExceptionDialog(e);
				}
			}
		});
		
		alert.showAndWait();
	}
	
	public static void showErrorDialog(String string, boolean english) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("错误");
		alert.setHeaderText(null);
		alert.setContentText(string);
		
		if (english) {
			alert.setTitle("Error");	
		}
		
		alert.showAndWait();
	}
	
	public static void showExceptionDialog(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception");
		alert.setHeaderText("Exception occurs");
		alert.setContentText(e.getMessage());
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();
		
		Text text = new Text("The exception stacktrace was:");
		
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
}
