package com.example.kursach_client1.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuAdminController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private Button changeLogAndPass;

    @FXML
    private Button filmButton;

    @FXML
    private Button hallButton;

    @FXML
    private Button kolTickets;

    @FXML
    private Label labelMessage;

    @FXML
    private Button listClients;

    @FXML
    private Button moneyButton;

    @FXML
    private Button posterButton;

    @FXML
    private Button seansButton;

    @FXML
    private Button statisticFilms;

    @FXML
    private Button statisticReturn;

    @FXML
    void EnterChangePoster(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(posterButton, "summareturn.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterClients(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(4);

        try {
            SceneSwitcher.sceneSwitcher(listClients, "clientsforadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(buttonExit, "login.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterFilms(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(2);
        Connection.client.sendObject(1);
        try {
            SceneSwitcher.sceneSwitcher(buttonExit, "workfilms.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterHall(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(hallButton, "hall.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterMoney(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(moneyButton, "revenue.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterNewLogin(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(changeLogAndPass, "changeloginadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterSeans(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(seansButton, "seanses.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterStatisticFilms(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(statisticFilms, "statisticfilms.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterStatisticReturn(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(statisticReturn, "statisticreturn.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterTickets(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(kolTickets, "ticketsadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
