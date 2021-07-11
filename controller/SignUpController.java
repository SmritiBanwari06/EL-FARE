package controller;

import Database.DatabaseHandler;
import com.sun.prism.shader.DrawSemiRoundRect_ImagePattern_AlphaTest_Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpUsername;

    @FXML
    private TextField SignUpFirstName;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    private RadioButton SignUpMale;

    @FXML
    private RadioButton SignUpFemale;

    @FXML
    private Button SignUpButton;

    @FXML
    void initialize() {




        SignUpButton.setOnAction(actionEvent -> {
            createUser();

            SignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login.fxml"));

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
    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = SignUpFirstName.getText();
        String lastname = SignUpLastName.getText();
        String username = SignUpUsername.getText();
        String password = SignUpPassword.getText();

        String gender = "";
        if(SignUpFemale.isSelected()){
            gender = "Female";
        }else gender = "Male";
        User user = new User(name, lastname, username, password, gender);
        databaseHandler.signUpUser(user);

    }

    public RadioButton getSignUpMale() {
        return SignUpMale;
    }

    public void setSignUpMale(RadioButton signUpMale) {
        SignUpMale = signUpMale;
    }
}
