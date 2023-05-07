package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ClientsForAdminController {

    @FXML
    private Button exitId1;

    ObservableList<Client> clientList;
    @FXML
    private TableView<Client> tableCl;
    @FXML
    private TableColumn<Client, String> fnameCl;

    @FXML
    private TableColumn<Client, String> lnameCl;

    @FXML
    private TableColumn<Client, String> numberCl;
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
        Object obj = Connection.client.readObject();
        List<Client> clients = CastList.castList(obj, Client.class);
        clientList = FXCollections.observableList(clients);
        fnameCl.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getFirst_name()));
        lnameCl.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getLast_name()));
        numberCl.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getPhone_number()));
        tableCl.setItems(clientList);

    }

}
