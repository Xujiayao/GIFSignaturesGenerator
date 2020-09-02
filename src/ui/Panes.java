package ui;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tools.Variables;

/**
 * @author Xujiayao
 */
public class Panes {
	
	public static Pane pane1() {
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
		text3.setLayoutY(115);
		
		Text text4 = new Text("选择配置类型");
		text4.setFont(new Font("Microsoft YaHei Bold", 18));
		text4.setWrappingWidth(600);
		text4.setLayoutX(10);
		text4.setLayoutY(176);
		
		Text text5 = new Text("请选择最适合您需求的配置类型。");
		text5.setFont(new Font("Microsoft YaHei", 14));
		text5.setWrappingWidth(600);
		text5.setLayoutX(10);
		text5.setLayoutY(216);
		
		ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("典型（推荐）");
		rb1.setToggleGroup(group);
		rb1.setStyle("-fx-text-fill: #000000");
		rb1.setFont(new Font("Microsoft YaHei", 14));
		rb1.setPrefWidth(600);
		rb1.setPrefHeight(19);
		rb1.setLayoutX(10);
		rb1.setLayoutY(240);
		rb1.setSelected(true);
		
		Text text6 = new Text("只需几个简单的步骤即可生成 projectFLY 签名图。");
		text6.setFont(new Font("Microsoft YaHei", 14));
		text6.setWrappingWidth(575);
		text6.setLayoutX(35);
		text6.setLayoutY(283);
		
		RadioButton rb2 = new RadioButton("自定义（高级）");
		rb2.setToggleGroup(group);
		rb2.setStyle("-fx-text-fill: #000000");
		rb2.setFont(new Font("Microsoft YaHei", 14));
		rb2.setPrefWidth(600);
		rb2.setPrefHeight(19);
		rb2.setLayoutX(10);
		rb2.setLayoutY(307);
		
		Text text7 = new Text("编辑签名图中的文本，选择签名图主题。推荐高级用户使用。");
		text7.setFont(new Font("Microsoft YaHei", 14));
		text7.setWrappingWidth(575);
		text7.setLayoutX(35);
		text7.setLayoutY(351);
		
		pane.getChildren().addAll(text1, text2, text3, text4, text5, rb1, text6, rb2, text7);
		
		if (Variables.language.equals("English")) {
			text1.setText("Welcome to PF Signatures Generator!");
			
			text2.setText("With PF Signatures Generator, you can produce projectFLY signatures in a professional way.");
			text3.setText("This wizard leads you step by step to collect all required informations to generate your signature.");
			
			text4.setText("Choose the type of configuration");
			
			text5.setText("Please choose the type of configuration that best suits your needs.");
			
			rb1.setText("Typical (Recommended)");
			
			text6.setText("Generate projectFLY signatures in a few simple steps.");

			rb2.setText("Custom (Advanced)");
			
			text7.setText("Edit the text in the signature, choose among different themes. Recommended for advanced users.");
		}
		
		return pane;
	}
}
