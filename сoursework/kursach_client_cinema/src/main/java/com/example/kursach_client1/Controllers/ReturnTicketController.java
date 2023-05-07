package com.example.kursach_client1.Controllers;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Ticket;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ReturnTicketController {

    ObservableList<Ticket> ticketsList;
    @FXML
    private TableColumn<Ticket, String> dateColumn;


    @FXML
    private Button returnButton;

    @FXML
    private Button exitId1;

    @FXML
    private TableColumn<Ticket, String> nameColumn;

    @FXML
    private TableColumn<Ticket, Number> placeColumn;

    @FXML
    private TableColumn<Ticket, Number> rowColumn;

    @FXML
    private TableColumn<Ticket, String> statusColumn;

    @FXML
    private TableView<Ticket> table;

    @FXML
    private TableColumn<Ticket, String> timeColumn;

    @FXML
    void EnterReturn(MouseEvent event) {
        Connection.client.sendObject(4);
        Connection.client.sendObject(3);
        Connection.client.sendObject(2);
        Ticket ticket = table.getSelectionModel().getSelectedItem();
        if(ticket != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Вы уверены что хотите вернуть билет?");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
                Connection.client.sendObject(ticket);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText(null);
                alert1.setContentText("Билет возвращён");
                alert1.showAndWait();
                try {
                    SceneSwitcher.sceneSwitcher(exitId1, "menuclient.fxml", "cinema", false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Вы не выбрали билет для возврата");
            alert.showAndWait();
        }


    }

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
        Connection.client.sendObject(4);
        Connection.client.sendObject(3);
        Connection.client.sendObject(1);

        Object obj = Connection.client.readObject();
        List<Ticket> tickets = CastList.castList(obj, Ticket.class);
        ticketsList = FXCollections.observableList(tickets);
        nameColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getId_time().getFilms().getName()));
        dateColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getId_time().getDay().toString()));
        timeColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleStringProperty(clientStringCellDataFeatures.getValue().getId_time().getTime().toString()));
        rowColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleIntegerProperty(clientStringCellDataFeatures.getValue().getId_place().getRow()));
        placeColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleIntegerProperty(clientStringCellDataFeatures.getValue().getId_place().getPlace()));
        table.setItems(ticketsList);
    }
}
