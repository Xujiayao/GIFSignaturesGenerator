package main;

import java.util.List;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tools.Updates;
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
		
		if (Variables.checkUpdates.equals("true")) {
			new Thread(new UpdateThread()).start();
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
		Updates.start(true);
	}
}
