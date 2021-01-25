module GIFSignaturesGenerator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.swing;
	requires java.desktop;
	requires de.jensd.fx.fontawesomefx.fontawesome;
	requires net.sf.image4j;
	requires org.dtools.javaini;
	requires commons.io;

	exports io.gitee.xujiayao147.gifSignaturesGenerator;
	exports io.gitee.xujiayao147.gifSignaturesGenerator.ui;
	exports io.gitee.xujiayao147.gifSignaturesGenerator.tools;
}