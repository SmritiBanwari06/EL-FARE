package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Database.DatabaseHandler;
import model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ListController {

    @FXML
    private ListView<Task> listTask;
    @FXML
    public TextField listTaskField;
    @FXML
    public TextField listDescriptionField;
    @FXML
    public Button listSaveTaskButton;

    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() throws SQLException {

        tasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.UserId);

        while (resultSet.next()) {

            Task task = new Task();
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            tasks.addAll(task);

            //System.out.println("User Task: " + resultSet.getString("task"));
        }


        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());

        listSaveTaskButton.setOnAction(actionEvent -> {
            addNewTask();
        });

    }

    public void addNewTask() {
            if(!listTaskField.getText().equals("")
                    || !listDescriptionField.getText().equals("")){

                Task myNewTask = new Task();

                Calendar calendar = Calendar.getInstance();

                java.sql.Timestamp timestamp =
                        new java.sql.Timestamp(calendar.getTimeInMillis());

                myNewTask.setUserId(AddItemController.UserId);
                myNewTask.setTask(listTaskField.getText().trim());
                myNewTask.setDescription(listDescriptionField.getText().trim());
                myNewTask.setDatecreated(timestamp);
                databaseHandler.insertTask(myNewTask);

                listTaskField.setText("");
                listDescriptionField.setText("");

                tasks.add(myNewTask);

            }
    }


}


