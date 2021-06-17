module GIFSignaturesGenerator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.swing;
	requires java.desktop;
	requires de.jensd.fx.fontawesomefx.fontawesome;
	requires net.sf.image4j;
	requires commons.io;
	requires jdk.crypto.ec;
	requires animated.gif.lib;
	requires com.google.gson;

	exports top.xujiayao.gifsigngen;
	exports top.xujiayao.gifsigngen.ui;
	exports top.xujiayao.gifsigngen.tools;
}