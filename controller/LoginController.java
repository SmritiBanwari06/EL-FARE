package controller;

import Database.DatabaseHandler;
import animations.Shaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {

    private int UserId;

    @FXML
    private TextField LoginUsername;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private Button LoginButton;

    @FXML
    private Button LoginSignUpButton;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();



        LoginButton.setOnAction(event -> {

            String loginText = LoginUsername.getText().trim();
            String loginPwd = LoginPassword.getText().trim();

            User user = new User();
            user.setUsername(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);

            int counter = 0;
            try {
                while (userRow.next()) {
                    counter++;

                    String name = userRow.getString("firstname");
                    UserId = userRow.getInt("userid");


                    System.out.println("Welcome!! " + name);

                }

                if (counter == 1) {

                    showAddItemScreen();

                }else {
                    Shaker userNameShaker = new Shaker(LoginUsername);
                    Shaker password = new Shaker(LoginPassword);
                    password.shake();
                    userNameShaker.shake();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }



        });

        LoginSignUpButton.setOnAction(event -> {

            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });



    }

    private void showAddItemScreen(){

        LoginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/addItem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();
        addItemController.setUserId(UserId);

        stage.showAndWait();
    }



}



