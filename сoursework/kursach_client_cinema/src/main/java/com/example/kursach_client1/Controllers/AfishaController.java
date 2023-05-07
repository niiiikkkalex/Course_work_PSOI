package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.stage.Window;

import static jdk.jfr.consumer.EventStream.openFile;

public class AfishaController {

    @FXML
    private Label choiseFile;

    @FXML
    private Button edit;

    @FXML
    private Button exitId1;

    File file;

    @FXML
    void EnterChoiseFile(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = null;
        file = fileChooser.showOpenDialog(stage);
        choiseFile.setText(file.getPath());
    }
    @FXML
    void EnterEdit(MouseEvent event) {
        if(file!=null){
            BuyTicketController.ChangeAfisha(file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Афиша изменена");
            alert.showAndWait();
            try {
                SceneSwitcher.sceneSwitcher(edit, "menuadmin.fxml", "cinema", false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Файл не выбран");
            alert.showAndWait();
        }
    }

    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(exitId1, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
