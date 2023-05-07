package com.example.kursach_client1.Controllers;

import java.io.IOException;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Login {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRegister;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        buttonRegister.setOnAction(actionEvent -> {

        });
    }
    public void Mouse(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.sceneSwitcher(buttonRegister, "regestration.fxml","cinema",false);
    }

    public void MouseAuth(MouseEvent mouseEvent) throws IOException {
        //Вот тут будешь флаг отправлять на вход, придумаешь сама что это будет
        Connection.client.sendObject(1);

        Connection.client.sendObject(loginField.getText());
        Connection.client.sendObject(passwordField.getText());
        int user = 0;
        //int flagOutput = 1;
        //flagOutput = (int) Connection.client.readObject();
        //if (flagOutput == 1) {
        user = (int) Connection.client.readObject();
        if (user == 1) {
            SceneSwitcher.sceneSwitcher(buttonLogin, "menuadmin.fxml", "cinema", false);
        } else if (user == 2) {
            SceneSwitcher.sceneSwitcher(buttonLogin, "menuclient.fxml", "cinema", false);
        } else if (user == 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неправильно введён логин или пароль");

            alert.showAndWait();
        }
        //}
    }
}