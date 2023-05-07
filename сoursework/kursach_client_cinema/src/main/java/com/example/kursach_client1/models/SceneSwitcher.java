package com.example.kursach_client1.models;

import com.example.kursach_client1.MainClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneSwitcher {
    public static void sceneSwitcher(Button button, String fxml, String title, boolean isModal) throws IOException {
        Parent root = FXMLLoader.load(MainClass.class.getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        if (isModal) {
            stage.initModality(Modality.APPLICATION_MODAL);
        } else {
            button.getScene().getWindow().hide();
        }
        stage.show();
    }
}
