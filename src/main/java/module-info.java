module GIFSignaturesGenerator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.swing;
	requires java.desktop;
	requires de.jensd.fx.fontawesomefx.fontawesome;
	requires net.sf.image4j;
	requires org.dtools.javaini;
	requires commons.io;
	requires jdk.crypto.ec;
	requires animated.gif.lib;

	exports top.xujiayao.gif_signatures_generator;
	exports top.xujiayao.gif_signatures_generator.ui;
	exports top.xujiayao.gif_signatures_generator.tools;
}