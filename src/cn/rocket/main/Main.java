package cn.rocket.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
* The main entrance of this program.
* <p>
* This program is used to copy a long string by 6 characters in once.
* @author RocketBD
* @version 1.0
*/
public class Main extends Application {
	
	/**
	* The main method in javafx thread.
	*/
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.setTitle("刷字程序");
			primaryStage.setOnCloseRequest(new MyEventHandler());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* The main method in the whole program.
	*/
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	* An inner class that implements window event handler to shut 
	* the app when the close button is clicked.
	*/
	class MyEventHandler implements javafx.event.EventHandler<WindowEvent>{
		@Override
		public void handle(WindowEvent event) {
			System.exit(0);
		}
	}
		
}
