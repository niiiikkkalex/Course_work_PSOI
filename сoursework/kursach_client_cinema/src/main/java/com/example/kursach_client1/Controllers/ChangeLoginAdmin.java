package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChangeLoginAdmin {

    @FXML
    private Button changeButton;

    @FXML
    private Button exitId1;

    @FXML
    private PasswordField lastpass;

    @FXML
    private TextField login;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField passConf;

    @FXML
    void EnterChange(MouseEvent event) {
        if(lastpass.getText().equals("") || login.getText().equals("") || pass.getText().equals("") || passConf.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Поля нельзя оставлять пустыми!");

            alert.showAndWait();
        }
        else {
            if (pass.getText().equals(passConf.getText())) {

                Connection.client.sendObject(3);
                Connection.client.sendObject(1);

                Connection.client.sendObject(lastpass.getText());
                Connection.client.sendObject(login.getText());
                Connection.client.sendObject(pass.getText());

                int newPass = 0;
                newPass = (int) Connection.client.readObject();
                if (newPass == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Старый пароль введён неверно");
                    alert.showAndWait();
                } else if (newPass == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setHeaderText(null);
                    alert.setContentText("Пароль и логин успешно изменены!");

                    alert.showAndWait();
                    try {
                        SceneSwitcher.sceneSwitcher(changeButton, "menuadmin.fxml", "cinema", false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    SceneSwitcher.sceneSwitcher(changeButton, "menuadmin.fxml", "cinema", false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введённые пароли не совпадают!");

                alert.showAndWait();
            }
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

