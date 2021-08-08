package cn.rocket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dialog.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.setTitle("\u5237\u5b57\u7a0b\u5e8f");
            primaryStage.setOnCloseRequest(new MyEventHandler());
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class MyEventHandler implements javafx.event.EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    }

}
