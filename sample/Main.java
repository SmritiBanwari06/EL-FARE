package sample;

import Database.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/view/list.fxml"));
        primaryStage.setTitle("EL FARE");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
