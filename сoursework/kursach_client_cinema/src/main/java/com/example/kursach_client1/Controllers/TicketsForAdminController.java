package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Film;
import entity.Ticket;
import javafx.beans.property.SimpleIntegerProperty;
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

public class TicketsForAdminController {

    @FXML
    private Button exitId1;

    ObservableList<Film> filmsList;
    @FXML
    private TableColumn<Film, Number> kolColumn;

    @FXML
    private TableColumn<Film, String> nameColumn;

    @FXML
    private TableView<Film> table;
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
        Connection.client.sendObject(5);

        Object obj = Connection.client.readObject();
        List<Film> films = CastList.castList(obj, Film.class);
        filmsList = FXCollections.observableList(films);
        nameColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getName()));
        kolColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleIntegerProperty(clientStringCellDataFeatures.getValue().getKol_tickets()));
        table.setItems(filmsList);
    }

}
