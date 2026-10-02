package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    
    @Override 
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/view/Principal.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.setMinWidth(0);
        stage.setMinHeight(0);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
