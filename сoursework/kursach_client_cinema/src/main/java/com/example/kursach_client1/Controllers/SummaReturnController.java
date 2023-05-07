package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DecimalFormat;

public class SummaReturnController {

    @FXML
    private Button exitId1;

    @FXML
    private Label money;

    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(exitId1, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        Connection.client.sendObject(3);
        Connection.client.sendObject(10);

        double revenue = 0;
        revenue = (double) Connection.client.readObject();
        String formattedDouble = new DecimalFormat("#0.00").format(revenue);
        money.setText(formattedDouble);
    }
}
