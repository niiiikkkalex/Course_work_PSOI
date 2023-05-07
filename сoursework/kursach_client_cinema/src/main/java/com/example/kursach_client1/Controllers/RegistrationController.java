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

public class RegistrationController {
    @FXML
    public TextField login;
    @FXML
    public Button exitId1;
    @FXML
    public Button registerId;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField number;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField passConf;

    public void Enter(MouseEvent mouseEvent) {
        if(fname.getText().equals("") || lname.getText().equals("") || number.getText().equals("") || pass.getText().equals("") || passConf.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Поля нельзя оставлять пустыми!");

            alert.showAndWait();
        }
        else{
            if (pass.getText().equals(passConf.getText())) {

                Connection.client.sendObject(2);

                Connection.client.sendObject(fname.getText()); //Типа вот тут ты говоришь серверу, что будет проходить регистрация
                Connection.client.sendObject(lname.getText()); //
                Connection.client.sendObject(number.getText());
                Connection.client.sendObject(login.getText());
                Connection.client.sendObject(pass.getText());

                int reg = 0;
                reg = (int) Connection.client.readObject();
                if(reg == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Такой пользователь уже существует!");
                    alert.showAndWait();
                }
                else if (reg == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setHeaderText(null);
                    alert.setContentText("Регистрация прошла успешно!");

                    alert.showAndWait();
                    try {
                        SceneSwitcher.sceneSwitcher(registerId, "login.fxml", "cinema", false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    SceneSwitcher.sceneSwitcher(registerId, "login.fxml", "cinema", false);
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
    public void EnterExit(MouseEvent mouseEvent) {
        try {
            SceneSwitcher.sceneSwitcher(exitId1, "login.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
