package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Film;
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

public class FilmsForClient {

    @FXML
    private Button exitId1;
    ObservableList<Film> filmsList;
    @FXML
    private TableColumn<Film, Number> columnAge;

    @FXML
    private TableColumn<Film, String> columnCountry;

    @FXML
    private TableColumn<Film, String> columnGenre;

    @FXML
    private TableColumn<Film, String> columnName;

    @FXML
    private TableColumn<Film, Number> columnTime;

    @FXML
    private TableView<Film> table;
    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(exitId1, "menuclient.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        Object obj = Connection.client.readObject();
        List<Film> films = CastList.castList(obj, Film.class);
        filmsList = FXCollections.observableList(films);
        columnName.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getName()));
        columnGenre.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getGanre()));
        columnCountry.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getCountry()));
        columnTime.setCellValueFactory(clientStringCellDataFeatures -> new SimpleIntegerProperty(clientStringCellDataFeatures.getValue().getTime()));
        columnAge.setCellValueFactory(clientStringCellDataFeatures -> new SimpleIntegerProperty(clientStringCellDataFeatures.getValue().getAge()));
        table.setItems(filmsList);

    }

}
