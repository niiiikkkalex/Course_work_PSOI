package com.example.kursach_client1.Controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Client;
import entity.Film;
import entity.Timetable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.SimpleTimeZone;

public class SeansesController {

    @FXML
    private DatePicker dataPicker;

    @FXML
    private DatePicker dateDelete;

    @FXML
    private DatePicker dateEdit;

    @FXML
    private DatePicker dateForView;

    List<Timetable> timetables;
    List<Film> films;
    @FXML
    private Button add;
    @FXML
    private Button buttonEdit;

    @FXML
    private Button deleteButton;

    @FXML
    private Button exitId1;

    @FXML
    private ChoiceBox filmAdd;

    @FXML
    private ChoiceBox filmEdit;

    @FXML
    private TableColumn<Timetable, String> nameColumn;

    @FXML
    private TextField price;

    @FXML
    private TextField priceEdit;

    @FXML
    private TableView<Timetable> tableId;

    @FXML
    private ChoiceBox timeAdd;

    @FXML
    private ChoiceBox timeEdit;

    @FXML
    private TableColumn<Timetable, String> timeColumn;

    @FXML
    private ChoiceBox timeDelete;

    ObservableList<Timetable> timetableList;
    @FXML
    void EnterExit(MouseEvent event) {
        try {
            SceneSwitcher.sceneSwitcher(exitId1, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void EnterAdd(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(3);
        Connection.client.sendObject(2);
        Timetable timetable = new Timetable();
        LocalDate date = dataPicker.getValue();
        timetable.setDay(Date.valueOf(date));
        timetable.setTime(Time.valueOf((String)timeAdd.getValue()));
        timetable.setPrice(Double.parseDouble(price.getText()));
        for(Film i : films){
            if(i.getName().equals(filmAdd.getValue())){
                timetable.setFilms(i);
                break;
            }
        }
        Connection.client.sendObject(timetable);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Сеанс добавлен");
        alert.showAndWait();
        try {
            SceneSwitcher.sceneSwitcher(add, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterDelete(MouseEvent event){
        Connection.client.sendObject(3);
        Connection.client.sendObject(3);
        Connection.client.sendObject(3);
        Timetable timetable = new Timetable();
        LocalDate date = dateDelete.getValue();
        timetable.setDay(Date.valueOf(date));
        timetable.setTime(Time.valueOf((String)timeDelete.getValue()));
        Connection.client.sendObject(timetable);
        int check = 0;
        check = (int)Connection.client.readObject();
        if(check == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Сеанс удалён");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Такого сеанса не существует");
            alert.showAndWait();
        }

        try {
            SceneSwitcher.sceneSwitcher(deleteButton, "menuadmin.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EnterEdit(MouseEvent event) {
        Connection.client.sendObject(3);
        Connection.client.sendObject(3);
        Connection.client.sendObject(4);
        Timetable timetable = new Timetable();
        LocalDate date = dateEdit.getValue();
        timetable.setDay(Date.valueOf(date));
        timetable.setTime(Time.valueOf((String)timeEdit.getValue()));
        timetable.setPrice(Double.parseDouble(priceEdit.getText()));
        for(Film i : films){
            if(i.getName().equals(filmEdit.getValue())){
                timetable.setFilms(i);
                break;
            }
        }
        Connection.client.sendObject(timetable);
        int check = 0;
        check = (int)Connection.client.readObject();
        if(check == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Сеанс отредактирован");
            alert.showAndWait();
            try {
                SceneSwitcher.sceneSwitcher(add, "menuadmin.fxml", "cinema", false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Сеанса на выбранную дату и выбранное время не создано");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        Connection.client.sendObject(3);
        Connection.client.sendObject(3);
        Connection.client.sendObject(1);
        //LocalDate date = dateDelete.getValue();
        Object obj = Connection.client.readObject();
        timetables = CastList.castList(obj, Timetable.class);
        timetableList = FXCollections.observableList(timetables);
        nameColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getFilms().getName()));
        timeColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getTime().toString()));
        tableId.setItems(timetableList);

        films = CastList.castList(Connection.client.readObject(), Film.class);
        for(Film i : films){
            filmAdd.getItems().add(i.getName());
            filmEdit.getItems().add(i.getName());
        }
        timeAdd.getItems().add("11:00:00");
        timeAdd.getItems().add("14:00:00");
        timeAdd.getItems().add("17:00:00");
        timeAdd.getItems().add("20:00:00");

        timeDelete.getItems().add("11:00:00");
        timeDelete.getItems().add("14:00:00");
        timeDelete.getItems().add("17:00:00");
        timeDelete.getItems().add("20:00:00");

        timeEdit.getItems().add("11:00:00");
        timeEdit.getItems().add("14:00:00");
        timeEdit.getItems().add("17:00:00");
        timeEdit.getItems().add("20:00:00");
    }

    public void onUpdate(Event event) {
        ObservableList<Timetable> seans = FXCollections.observableArrayList();
        LocalDate dateView = dateForView.getValue();
        for(Timetable i : timetableList){
            if(i.getDay().toString().equals(Date.valueOf(dateView).toString())) {
                seans.add(i);
            }
        }
        tableId.setItems(seans);
    }
}
