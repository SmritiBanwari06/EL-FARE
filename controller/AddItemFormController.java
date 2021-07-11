package controller;

import Database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Task;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class AddItemFormController {

    public static int UserId;


    private DatabaseHandler databaseHandler;

    @FXML
    private Label successLabel;

    @FXML
    private Button todosButton;


    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button SaveTaskButton;

    @FXML
    void initialize() {


        databaseHandler = new DatabaseHandler();

        SaveTaskButton.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {
                System.out.println("User Id: " + AddItemFormController.UserId);

                task.setUserId((AddItemFormController.UserId));
                task.setUserId(getUserId());
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                databaseHandler.insertTask(task);

                successLabel.setVisible(true);

                todosButton.setVisible(true);
                int taskNumber = 0;
                try {
                    taskNumber = databaseHandler.getAllTask(AddItemController.UserId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                todosButton.setText("My 2Do's: " + "("+taskNumber+")");

                taskField.setText("");
                descriptionField.setText("");
                todosButton.setOnAction(event1 -> {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/list.fxml"));

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

                //System.out.println("Task Added Successfully!");



            }else {
                System.out.println("Nothing added!");

            }

        });




    }

    public int getUserId() {
        return this.UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
        System.out.println(this.UserId);
    }
}
