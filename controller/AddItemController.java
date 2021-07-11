package controller;

import animations.Shaker;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ResourceBundle;

public class AddItemController {

    public static int UserId;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML

    private ImageView addButton;

    @FXML
    private Label noTaskLabel;

    @FXML
    void initialize() {


        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);

            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), noTaskLabel);


            System.out.println("Added Clicked");
            addButton.relocate(0, 7);
            noTaskLabel.relocate(0, 55);


            addButton.setOpacity(0);
            noTaskLabel.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();

            try {

                AnchorPane formPane =
                        FXMLLoader.load(getClass().getResource("/view/addItemForm.fxml"));

                AddItemFormController.UserId = getUserId();

               // AddItemFormController  addItemController = new AddItemFormController();
                // addItemController.setUserId(getUserId());


                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000), formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }


    public void setUserId(int UserId) {
        this.UserId = UserId;
        System.out.println("User Id is " + this.UserId);
    }

    public int getUserId(){
        return this.UserId;
    }
}



