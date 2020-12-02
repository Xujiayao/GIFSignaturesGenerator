package ui;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tools.Variables;

/**
 * @author Xujiayao
 */
public class Panes {
	
	public static int paneShowing;

	public static Pane pane2() {
		paneShowing = 2;
		
		Pane pane = new Pane();
		pane.setPrefSize(620, 518);
		pane.setLayoutX(180);
		pane.setLayoutY(40);
		pane.setStyle("-fx-background-color: #FFF");
		
		Text title = new Text("解析结果");
		title.setFont(new Font("Microsoft YaHei Bold", 18));
		title.setWrappingWidth(600);
		title.setLayoutX(10);
		title.setLayoutY(28);
		
		Text text0 = new Text("请检查您的解析结果。如果有任何错误，请告诉我。");
		text0.setFont(new Font("Microsoft YaHei", 14));
		text0.setWrappingWidth(600);
		text0.setLayoutX(10);
		text0.setLayoutY(67);
		
		Text text1 = new Text("显示名称：");
		text1.setFont(new Font("Microsoft YaHei", 14));
		text1.setWrappingWidth(170);
		text1.setLayoutX(10);
		text1.setLayoutY(107);
		
		TextField field1 = new TextField(Variables.loginData[1]);
		field1.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field1.setPrefSize(420, 25);
		field1.setFont(new Font("Microsoft YaHei", 12));
		field1.setEditable(false);
		field1.setLayoutX(190);
		field1.setLayoutY(90);
		
		Text text2 = new Text("用户名：");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setWrappingWidth(170);
		text2.setLayoutX(10);
		text2.setLayoutY(142);
		
		TextField field2 = new TextField(Variables.loginData[0]);
		field2.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field2.setPrefSize(420, 25);
		field2.setFont(new Font("Microsoft YaHei", 12));
		field2.setEditable(false);
		field2.setLayoutX(190);
		field2.setLayoutY(125);

		Text text3 = new Text("总小时数：");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setWrappingWidth(170);
		text3.setLayoutX(10);
		text3.setLayoutY(178);
		
		TextField field3 = new TextField(Variables.profileData[3]);
		field3.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field3.setPrefSize(420, 25);
		field3.setFont(new Font("Microsoft YaHei", 12));
		field3.setEditable(false);
		field3.setLayoutX(190);
		field3.setLayoutY(160);
		
		Text text4 = new Text("总航班数：");
		text4.setFont(new Font("Microsoft YaHei", 14));
		text4.setWrappingWidth(170);
		text4.setLayoutX(10);
		text4.setLayoutY(213);
		
		TextField field4 = new TextField(Variables.profileData[2]);
		field4.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field4.setPrefSize(420, 25);
		field4.setFont(new Font("Microsoft YaHei", 12));
		field4.setEditable(false);
		field4.setLayoutX(190);
		field4.setLayoutY(195);
		
		Text text5 = new Text("个人简介：（可编辑）");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setWrappingWidth(170);
		text5.setLayoutX(10);
		text5.setLayoutY(248);
		
		TextField field5 = new TextField(Variables.profileData[0]);
		field5.setStyle("-fx-background-color: #F5F5F5; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field5.setPrefSize(420, 25);
		field5.setFont(new Font("Microsoft YaHei", 12));
		field5.setLayoutX(190);
		field5.setLayoutY(230);

		Text text6 = new Text("喜爱的航空器：");
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setWrappingWidth(170);
		text6.setLayoutX(10);
		text6.setLayoutY(282);
		
		TextField field6 = new TextField(Variables.profileData[5] + " / " +  Variables.profileData[4]);
		field6.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field6.setPrefSize(420, 25);
		field6.setFont(new Font("Microsoft YaHei", 12));
		field6.setEditable(false);
		field6.setLayoutX(190);
		field6.setLayoutY(265);

		Text text7 = new Text("喜爱的机场：");
		text7.setFont(new Font("Microsoft YaHei", 14));
		text7.setWrappingWidth(170);
		text7.setLayoutX(10);
		text7.setLayoutY(318);
		
		TextField field7 = new TextField("HAVE NOT DONE YET");
		field7.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field7.setPrefSize(420, 25);
		field7.setFont(new Font("Microsoft YaHei", 12));
		field7.setEditable(false);
		field7.setLayoutX(190);
		field7.setLayoutY(300);
		
		Text text8 = new Text("喜爱的路线：");
		text8.setFont(new Font("Microsoft YaHei", 14));
		text8.setWrappingWidth(170);
		text8.setLayoutX(10);
		text8.setLayoutY(353);
		
		TextField field8 = new TextField("HAVE NOT DONE YET");
		field8.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field8.setPrefSize(420, 25);
		field8.setFont(new Font("Microsoft YaHei", 12));
		field8.setEditable(false);
		field8.setLayoutX(190);
		field8.setLayoutY(335);
		
		Text text9 = new Text("已访问国家：");
		text9.setFont(new Font("Microsoft YaHei", 14));
		text9.setWrappingWidth(170);
		text9.setLayoutX(10);
		text9.setLayoutY(388);
		
		TextField field9 = new TextField("HAVE NOT DONE YET");
		field9.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field9.setPrefSize(420, 25);
		field9.setFont(new Font("Microsoft YaHei", 12));
		field9.setEditable(false);
		field9.setLayoutX(190);
		field9.setLayoutY(370);
		
		Text text10 = new Text("平均接地率：");
		text10.setFont(new Font("Microsoft YaHei", 14));
		text10.setWrappingWidth(170);
		text10.setLayoutX(10);
		text10.setLayoutY(423);
		
		TextField field10 = new TextField(Variables.profileData[6] + " fpm");
		field10.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field10.setPrefSize(420, 25);
		field10.setFont(new Font("Microsoft YaHei", 12));
		field10.setEditable(false);
		field10.setLayoutX(190);
		field10.setLayoutY(405);
		
		Text text11 = new Text("已完成成就：");
		text11.setFont(new Font("Microsoft YaHei", 14));
		text11.setWrappingWidth(170);
		text11.setLayoutX(10);
		text11.setLayoutY(458);
		
		TextField field11 = new TextField(Variables.profileData[1]);
		field11.setStyle("-fx-background-color: #D1D0CE; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #25292E; -fx-text-inner-color: #25292E");
		field11.setPrefSize(420, 25);
		field11.setFont(new Font("Microsoft YaHei", 12));
		field11.setEditable(false);
		field11.setLayoutX(190);
		field11.setLayoutY(440);
		
		pane.getChildren().addAll(title, text0, text1, field1, text2, field2, text3, field3, text4, field4, text5, field5, text6, field6, text7, field7, text8, field8, text9, field9, text10, field10, text11, field11);
		
		if (Variables.language.equals("English")) {
			title.setText("Parse Results");
			
			text0.setText("Please check your parse results. If there are any errors, please let me know.");
			
			text1.setText("Display Name:");
			text2.setText("Username:");
			text3.setText("Total Hours:");
			text4.setText("Total Flights:");
			text5.setText("Biography: (Editable)");
			text6.setText("Fav Aircraft:");
			text7.setText("Fav Airport:");
			text8.setText("Fav Route:");
			text9.setText("Visited Countries:");
			text10.setText("Avg Ldg Rate:");
			text11.setText("Achvs Completed:");
		}
		
		return pane;
	}
	
	public static Pane pane1() {
		paneShowing = 1;
		
		Pane pane = new Pane();
		pane.setPrefSize(620, 518);
		pane.setLayoutX(180);
		pane.setLayoutY(40);
		pane.setStyle("-fx-background-color: #FFF");
		
		Text text1 = new Text("欢迎使用PF签名图生成工具！");
		text1.setFont(new Font("Microsoft YaHei Bold", 18));
		text1.setWrappingWidth(600);
		text1.setLayoutX(10);
		text1.setLayoutY(28);
		
		Text text2 = new Text("使用PF签名图生成工具，您可以以专业的方式生成 projectFLY 签名图。");
		text2.setFont(new Font("Microsoft YaHei", 14));
		text2.setWrappingWidth(600);
		text2.setLayoutX(10);
		text2.setLayoutY(67);
		
		Text text3 = new Text("此向导将引导您逐步收集生成您的签名图所需的全部信息。");
		text3.setFont(new Font("Microsoft YaHei", 14));
		text3.setWrappingWidth(600);
		text3.setLayoutX(10);
		text3.setLayoutY(113);
		
		Text text4 = new Text("选择配置类型");
		text4.setFont(new Font("Microsoft YaHei Bold", 18));
		text4.setWrappingWidth(600);
		text4.setLayoutX(10);
		text4.setLayoutY(173);
		
		Text text5 = new Text("请选择最适合您需求的配置类型。");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setWrappingWidth(600);
		text5.setLayoutX(10);
		text5.setLayoutY(212);
		
		ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("自定义（推荐）");
		rb1.setToggleGroup(group);
		rb1.setStyle("-fx-text-fill: #000000");
		rb1.setFont(new Font("Microsoft YaHei", 14));
		rb1.setLayoutX(10);
		rb1.setLayoutY(236);
		rb1.setSelected(true);
		
		Text text6 = new Text("编辑签名图中的文本，选择签名图主题。推荐所有用户使用。");
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setWrappingWidth(575);
		text6.setLayoutX(35);
		text6.setLayoutY(279);
		
		RadioButton rb2 = new RadioButton("简易（将在新版本上线）");
		rb2.setToggleGroup(group);
		rb2.setStyle("-fx-text-fill: #000000");
		rb2.setFont(new Font("Microsoft YaHei", 14));
		rb2.setLayoutX(10);
		rb2.setLayoutY(320);
		rb2.setDisable(true);
		
		Text text7 = new Text("只需几个简易的步骤即可生成 projectFLY 签名图。");
		text7.setFont(new Font("Microsoft YaHei", 14));
		text7.setWrappingWidth(575);
		text7.setLayoutX(35);
		text7.setLayoutY(363);
		text7.setFill(Color.web("#999999"));
		
		pane.getChildren().addAll(text1, text2, text3, text4, text5, rb1, text6, rb2, text7);
		
		if (Variables.language.equals("English")) {
			text1.setText("Welcome to PF Signatures Generator!");
			
			text2.setText("With PF Signatures Generator, you can produce projectFLY signatures in a professional way.");
			text3.setText("This wizard leads you step by step to collect all required informations to generate your signature.");
			
			text4.setText("Choose the type of configuration");
			
			text5.setText("Please choose the type of configuration that best suits your needs.");
			
			rb1.setText("Custom (Recommended)");
			text6.setText("Edit the texts in the signature, choose among different themes. Recommended for all users.");

			rb2.setText("Simple (Will be available in the new version)");
			text7.setText("Generate projectFLY signatures in a few simple steps.");
		}
		
		return pane;
	}
}
