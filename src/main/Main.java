package main;

import java.awt.SystemTray;
import java.util.List;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tools.Update;
import tools.Variables;
import ui.Dialogs;
import ui.LoginUI;

/**
 * @author Xujiayao
 */
public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Variables.init();
		
		checkFont();
		
		new LoginUI().start(stage);
		
		if (Variables.checkUpdates == true) {
			new Thread(new UpdateThread()).start();
		}
		
		if (SystemTray.isSupported()) {
			tools.SystemTray.displayTray();
		}
	}
	
	private void checkFont() {
		List<String> list = Font.getFamilies();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals("Microsoft YaHei")){
				break;
			}
			
			if(i == list.size() - 1){
				Dialogs.showErrorDialog("Font Microsoft YaHei not found, exiting now.", true);
				
				System.exit(1);
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class UpdateThread implements Runnable {
	@Override
	public void run() {
		Update.start(true);
	}
}
