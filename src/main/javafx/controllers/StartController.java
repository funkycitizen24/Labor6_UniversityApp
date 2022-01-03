package javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    private Button idClose;

    private Stage stage;
    private Stage stage2;
    private Stage stage3;

    //Constructor
    public StartController() {
        idClose = new Button();
        stage = new Stage();
        stage2 = new Stage();
        stage3 = new Stage();
    }

    /**
     * This method closes the start window.
     */
    public void closeButtonAction(){
        stage = (Stage) idClose.getScene().getWindow();
        stage.close();
    }

    /**
     * This method opens the new window for student.
     * @throws IOException if any problems occur by loading the XML file
     */
    public void showStudentWindow() throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("Student.fxml"));
        stage2.setTitle("Student Area");
        stage2.setScene(new Scene(root1, 700, 400));
        stage2.show();
    }

    /**
     * This method opens the new window for teacher.
     * @throws IOException if any problems occur by loading the XML file
     */
    public void showTeacherWindow() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        stage3.setTitle("Teacher Area");
        stage3.setScene(new Scene(root2, 700, 400));
        stage3.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}