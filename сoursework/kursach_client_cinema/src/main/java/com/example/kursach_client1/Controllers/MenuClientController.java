package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class MenuClientController {

    @FXML
    private Button buttonBuyTicket;

    @FXML
    private Button buttonChangeLogin;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonFilms;

    @FXML
    private Button buttonReturnTicket;

    @FXML
    private Button buttonTickets;

    @FXML
    private Button exitButton;

    @FXML
    private Label labelMessage;

    @FXML
    void EnterBuyTicket(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(buttonBuyTicket, "buyticket.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterChangeLogin(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(buttonChangeLogin, "changedataclient.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterDelete(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(null);
        alert.setContentText("Вы уверены что хотите удалить аккаунт?");

        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            Connection.client.sendObject(4);
            Connection.client.sendObject(5);

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Аккаунт удалён");
            alert1.showAndWait();
            try {
                SceneSwitcher.sceneSwitcher(buttonDelete, "login.fxml", "cinema", false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(exitButton, "login.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterFilms(MouseEvent event) {
        Connection.client.sendObject(4);
        Connection.client.sendObject(1);
        try {
            SceneSwitcher.sceneSwitcher(buttonFilms, "filmsforclient.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterReturnTicket(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(buttonReturnTicket, "returnticket.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterTickets(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(buttonTickets, "histiryticket.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
