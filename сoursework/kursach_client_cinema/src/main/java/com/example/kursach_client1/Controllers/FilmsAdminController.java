package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Client;
import entity.Film;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

public class FilmsAdminController {

    @FXML
    private Button addFilmButton;

    ObservableList<Film> filmsList;
    @FXML
    private TextField age;

    @FXML
    private TextField ageEdit;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonExit;

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
    private TextField country;

    @FXML
    private TextField countryEdit;

    @FXML
    private Button editButton;

    @FXML
    private TextField genre;

    @FXML
    private TextField genreEdit;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField name;

    @FXML
    private TextField nameEdit;

    @FXML
    private TextField time;

    @FXML
    private TextField timeEdit;
    @FXML
    private TableView<Film> table;
    @FXML
    private ChoiceBox deleteChoice;
    @FXML
    private ChoiceBox editChoice;
    @FXML
    void EnterAddFilm(MouseEvent event) {
        if (name.getText().equals("") || genre.getText().equals("") || country.getText().equals("") || time.getText().equals("") || age.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Поля нельзя оставлять пустыми!");

            alert.showAndWait();
        } else {
            Connection.client.sendObject(3);
            Connection.client.sendObject(2);
            Connection.client.sendObject(2);

            Connection.client.sendObject(name.getText());
            Connection.client.sendObject(genre.getText());
            Connection.client.sendObject(country.getText());
            Connection.client.sendObject(Integer.parseInt(time.getText()));
            Connection.client.sendObject(Integer.parseInt(age.getText()));

            int addFilm = 0;
            addFilm = (int) Connection.client.readObject();
            if (addFilm == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Такой фильм уже добавлен!");
                alert.showAndWait();
            } else if (addFilm == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);
                alert.setContentText("Фильм успешно добавлен!");

                alert.showAndWait();

                try {
                    SceneSwitcher.sceneSwitcher(addFilmButton, "menuadmin.fxml", "cinema", false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        }

        @FXML
        void EnterExit(MouseEvent event){
            try {
                SceneSwitcher.sceneSwitcher(buttonExit, "menuadmin.fxml", "cinema", false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    @FXML
    void EnterDeleteFilm(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(2);
        Connection.client.sendObject(3);
        for(int i = 0; i < filmsList.size(); i++){
            if(filmsList.get(i).getName().equals(deleteChoice.getValue())){
                Connection.client.sendObject(filmsList.get(i));
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Фильм удалён");
        alert.showAndWait();
        try {
            SceneSwitcher.sceneSwitcher(buttonDelete, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterEdit(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(2);
        Connection.client.sendObject(4);
        for(int i = 0; i < filmsList.size(); i++){
            if(filmsList.get(i).getName().equals(editChoice.getValue())){
                filmsList.get(i).setName(nameEdit.getText());
                filmsList.get(i).setCountry(countryEdit.getText());
                filmsList.get(i).setGanre(genreEdit.getText());
                filmsList.get(i).setTime(Integer.parseInt(timeEdit.getText()));
                filmsList.get(i).setAge(Integer.parseInt(ageEdit.getText()));
                Connection.client.sendObject(filmsList.get(i));
            }
        }
/*        Connection.client.sendObject(nameEdit.getText());
        Connection.client.sendObject(genreEdit.getText());
        Connection.client.sendObject(countryEdit.getText());
        Connection.client.sendObject(Integer.parseInt(timeEdit.getText()));
        Connection.client.sendObject(Integer.parseInt(ageEdit.getText()));*/

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Фильм отредактирован");
        alert.showAndWait();
        try {
            SceneSwitcher.sceneSwitcher(editButton, "menuadmin.fxml", "cinema", false);
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
        for(int i = 0; i < filmsList.size(); i++){
            deleteChoice.getItems().add(filmsList.get(i).getName());
        }
        for(int i = 0; i < filmsList.size(); i++){
            editChoice.getItems().add(filmsList.get(i).getName());
        }
    }
    }

