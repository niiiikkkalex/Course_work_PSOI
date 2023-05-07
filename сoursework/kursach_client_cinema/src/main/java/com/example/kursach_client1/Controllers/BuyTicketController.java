package com.example.kursach_client1.Controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;

public class BuyTicketController {

    public Button choosePane;
    List<Timetable> timetables;
    List<Place> places = new ArrayList<>();
    List<Ticket> allTickets;
    @FXML
    private Button buyButton;

    @FXML
    private DatePicker dateFilms;

    @FXML
    private Button exitId1;

    @FXML
    private ChoiceBox film;

    @FXML
    private AnchorPane plasesPane;

    @FXML
    private ImageView afisha1;
    @FXML
    private Button s11;

    @FXML
    private Button s12;

    @FXML
    private Button s13;

    @FXML
    private Button s14;

    @FXML
    private Button s15;

    @FXML
    private Button s16;

    @FXML
    private Button s17;

    @FXML
    private Button s18;

    @FXML
    private Button s19;

    @FXML
    private Button s110;

    @FXML
    private Button s21;

    @FXML
    private Button s22;

    @FXML
    private Button s23;

    @FXML
    private Button s24;

    @FXML
    private Button s25;

    @FXML
    private Button s26;

    @FXML
    private Button s27;

    @FXML
    private Button s28;

    @FXML
    private Button s29;

    @FXML
    private Button s210;

    @FXML
    private Button s31;

    @FXML
    private Button s32;

    @FXML
    private Button s33;

    @FXML
    private Button s34;

    @FXML
    private Button s35;

    @FXML
    private Button s36;

    @FXML
    private Button s37;

    @FXML
    private Button s38;

    @FXML
    private Button s39;

    @FXML
    private Button s310;

    @FXML
    private Button s41;

    @FXML
    private Button s42;

    @FXML
    private Button s43;

    @FXML
    private Button s44;

    @FXML
    private Button s45;

    @FXML
    private Button s46;

    @FXML
    private Button s47;

    @FXML
    private Button s48;

    @FXML
    private Button s49;

    @FXML
    private Button s410;

    @FXML
    private Button s51;

    @FXML
    private Button s52;

    @FXML
    private Button s53;

    @FXML
    private Button s54;

    @FXML
    private Button s55;

    @FXML
    private Button s56;

    @FXML
    private Button s57;

    @FXML
    private Button s58;

    @FXML
    private Button s59;

    @FXML
    private Button s510;

    @FXML
    private Button s61;

    @FXML
    private Button s62;

    @FXML
    private Button s63;

    @FXML
    private Button s64;

    @FXML
    private Button s65;

    @FXML
    private Button s66;

    @FXML
    private Button s67;

    @FXML
    private Button s68;

    @FXML
    private Button s69;

    @FXML
    private Button s610;

    @FXML
    private Button s71;

    @FXML
    private Button s72;

    @FXML
    private Button s73;

    @FXML
    private Button s74;

    @FXML
    private Button s75;

    @FXML
    private Button s76;

    @FXML
    private Button s77;

    @FXML
    private Button s78;

    @FXML
    private Button s79;

    @FXML
    private Button s710;

    @FXML
    private Button s81;

    @FXML
    private Button s82;

    @FXML
    private Button s83;

    @FXML
    private Button s84;

    @FXML
    private Button s85;

    @FXML
    private Button s86;

    @FXML
    private Button s87;

    @FXML
    private Button s88;

    @FXML
    private Button s89;

    @FXML
    private Button s810;

    @FXML
    private Label price;

    @FXML
    private ChoiceBox time;

    Timetable seans;

    static void ChangeAfisha(File file){
        Image picture = new Image(file.getPath());
       // afisha1.setImage(picture);
    }
    @FXML
    void EnterBuy(MouseEvent event) {
        Connection.client.sendObject(4);
        Connection.client.sendObject(2);
        Connection.client.sendObject(2);
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0; i < places.size(); i++){
            tickets.add(new Ticket(0,null, seans, places.get(i),"куплен"));
        }
        Connection.client.sendObject(tickets);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Билеты куплены!");
        alert.showAndWait();

        try {
            SceneSwitcher.sceneSwitcher(choosePane, "menuclient.fxml", "cinema", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private static byte filmCheck = 0;
    List<Timetable> buffer1;

    @FXML
    void onUpdateDate(Event event) {
        filmCheck = 0;
        film.getItems().clear();
        time.getItems().clear();
        price.setText("");
        LocalDate dateFilm = dateFilms.getValue();
        buffer1 = new ArrayList<>();
        for (Timetable i : timetables) {
            if (i.getDay().toString().equals(Date.valueOf(dateFilm).toString())) {
                buffer1.add(i);
                film.getItems().add(i.getFilms().getName());
            }
        }
    }

    @FXML
    void onUpdateFilm(Event event) {
        for (Timetable i : buffer1) {
            if (i.getFilms().getName().equals(film.getValue().toString()) && (filmCheck & 1) == 0) {
                time.getItems().add(i.getTime().toString());
                filmCheck++;
            }
        }
        if ((filmCheck & 1) == 0) time.getItems().clear();
    }

    @FXML
    void onUpdateTime(Event event) {
        for (Timetable i : buffer1) {
            if (i.getTime().toString().equals(time.getValue().toString()) && (filmCheck & 1) == 1) {
                price.setText(String.valueOf(i.getPrice()));
                filmCheck++;
                seans = i;
                break;
            }
        }
        LocalDate localDate = dateFilms.getValue();
        choosePlace(Date.valueOf(localDate).toString(), film.getValue().toString(), time.getValue().toString());
    }

    @FXML
    void initialize() {
        Connection.client.sendObject(4);
        Connection.client.sendObject(2);
        Connection.client.sendObject(1);
        allTickets = CastList.castList(Connection.client.readObject(), Ticket.class);
        timetables = CastList.castList(Connection.client.readObject(), Timetable.class);
    }


    void choosePlace(String date, String film, String time) {
        plasesPane.toFront();
        for(Ticket i : allTickets) {
            if (i.getId_time().getTime().toString().equals(time) && i.getId_time().getDay().toString().equals(date) && i.getId_time().getFilms().getName().equals(film) && i.getStatus().equals("куплен")) {
                int rowNumber = i.getId_place().getRow();
                int placeNumber = i.getId_place().getPlace();
                switch (rowNumber) {
                    case 1: {
                        switch (placeNumber) {
                            case 1: {
                                s11.setDisable(true);
                                break;
                            }
                            case 2: {
                                s12.setDisable(true);
                                break;
                            }
                            case 3: {
                                s13.setDisable(true);
                                break;
                            }
                            case 4: {
                                s14.setDisable(true);
                                break;
                            }
                            case 5: {
                                s15.setDisable(true);
                                break;
                            }
                            case 6: {
                                s16.setDisable(true);
                                break;
                            }
                            case 7: {
                                s17.setDisable(true);
                                break;
                            }
                            case 8: {
                                s18.setDisable(true);
                                break;
                            }
                            case 9: {
                                s19.setDisable(true);
                                break;
                            }
                            case 10: {
                                s110.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 2: {
                        switch (placeNumber) {
                            case 1: {
                                s21.setDisable(true);
                                break;
                            }
                            case 2: {
                                s22.setDisable(true);
                                break;
                            }
                            case 3: {
                                s23.setDisable(true);
                                break;
                            }
                            case 4: {
                                s24.setDisable(true);
                                break;
                            }
                            case 5: {
                                s25.setDisable(true);
                                break;
                            }
                            case 6: {
                                s26.setDisable(true);
                                break;
                            }
                            case 7: {
                                s27.setDisable(true);
                                break;
                            }
                            case 8: {
                                s28.setDisable(true);
                                break;
                            }
                            case 9: {
                                s29.setDisable(true);
                                break;
                            }
                            case 10: {
                                s210.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 3: {
                        switch (placeNumber) {
                            case 1: {
                                s31.setDisable(true);
                                break;
                            }
                            case 2: {
                                s32.setDisable(true);
                                break;
                            }
                            case 3: {
                                s33.setDisable(true);
                                break;
                            }
                            case 4: {
                                s34.setDisable(true);
                                break;
                            }
                            case 5: {
                                s35.setDisable(true);
                                break;
                            }
                            case 6: {
                                s36.setDisable(true);
                                break;
                            }
                            case 7: {
                                s37.setDisable(true);
                                break;
                            }
                            case 8: {
                                s38.setDisable(true);
                                break;
                            }
                            case 9: {
                                s39.setDisable(true);
                                break;
                            }
                            case 10: {
                                s310.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 4: {
                        switch (placeNumber) {
                            case 1: {
                                s41.setDisable(true);
                                break;
                            }
                            case 2: {
                                s42.setDisable(true);
                                break;
                            }
                            case 3: {
                                s43.setDisable(true);
                                break;
                            }
                            case 4: {
                                s44.setDisable(true);
                                break;
                            }
                            case 5: {
                                s45.setDisable(true);
                                break;
                            }
                            case 6: {
                                s46.setDisable(true);
                                break;
                            }
                            case 7: {
                                s47.setDisable(true);
                                break;
                            }
                            case 8: {
                                s48.setDisable(true);
                                break;
                            }
                            case 9: {
                                s49.setDisable(true);
                                break;
                            }
                            case 10: {
                                s410.setDisable(true);
                                break;
                            }
                        }
                        break;

                    }
                    case 5: {
                        switch (placeNumber) {
                            case 1: {
                                s51.setDisable(true);
                                break;
                            }
                            case 2: {
                                s52.setDisable(true);
                                break;
                            }
                            case 3: {
                                s53.setDisable(true);
                                break;
                            }
                            case 4: {
                                s54.setDisable(true);
                                break;
                            }
                            case 5: {
                                s55.setDisable(true);
                                break;
                            }
                            case 6: {
                                s56.setDisable(true);
                                break;
                            }
                            case 7: {
                                s57.setDisable(true);
                                break;
                            }
                            case 8: {
                                s58.setDisable(true);
                                break;
                            }
                            case 9: {
                                s59.setDisable(true);
                                break;
                            }
                            case 10: {
                                s510.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        switch (placeNumber) {
                            case 1: {
                                s61.setDisable(true);
                                break;
                            }
                            case 2: {
                                s62.setDisable(true);
                                break;
                            }
                            case 3: {
                                s63.setDisable(true);
                                break;
                            }
                            case 4: {
                                s64.setDisable(true);
                                break;
                            }
                            case 5: {
                                s65.setDisable(true);
                                break;
                            }
                            case 6: {
                                s66.setDisable(true);
                                break;
                            }
                            case 7: {
                                s67.setDisable(true);
                                break;
                            }
                            case 8: {
                                s68.setDisable(true);
                                break;
                            }
                            case 9: {
                                s69.setDisable(true);
                                break;
                            }
                            case 10: {
                                s610.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 7: {
                        switch (placeNumber) {
                            case 1: {
                                s71.setDisable(true);
                                break;
                            }
                            case 2: {
                                s72.setDisable(true);
                                break;
                            }
                            case 3: {
                                s73.setDisable(true);
                                break;
                            }
                            case 4: {
                                s74.setDisable(true);
                                break;
                            }
                            case 5: {
                                s75.setDisable(true);
                                break;
                            }
                            case 6: {
                                s76.setDisable(true);
                                break;
                            }
                            case 7: {
                                s77.setDisable(true);
                                break;
                            }
                            case 8: {
                                s78.setDisable(true);
                                break;
                            }
                            case 9: {
                                s79.setDisable(true);
                                break;
                            }
                            case 10: {
                                s710.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                    case 8: {
                        switch (placeNumber) {
                            case 1: {
                                s81.setDisable(true);
                                break;
                            }
                            case 2: {
                                s82.setDisable(true);
                                break;
                            }
                            case 3: {
                                s83.setDisable(true);
                                break;
                            }
                            case 4: {
                                s84.setDisable(true);
                                break;
                            }
                            case 5: {
                                s85.setDisable(true);
                                break;
                            }
                            case 6: {
                                s86.setDisable(true);
                                break;
                            }
                            case 7: {
                                s87.setDisable(true);
                                break;
                            }
                            case 8: {
                                s88.setDisable(true);
                                break;
                            }
                            case 9: {
                                s89.setDisable(true);
                                break;
                            }
                            case 10: {
                                s810.setDisable(true);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @FXML
    void seatSelection(ActionEvent event) {
        int row = 0;
        int place = 0;
        if(event.getSource()==s11){
            row = 1;
            place = 1;
        }
        else if(event.getSource()==s12){
            row = 1;
            place = 2;
        }
        else if(event.getSource()==s13){
            row = 1;
            place = 3;
        }
        else if(event.getSource()==s14){
            row = 1;
            place = 4;
        }
        else if(event.getSource()==s15){
            row = 1;
            place = 5;
        }
        else if(event.getSource()==s16){
            row = 1;
            place = 6;
        }
        else if(event.getSource()==s17){
            row = 1;
            place = 7;
        }
        else if(event.getSource()==s18){
            row = 1;
            place = 8;
        }
        else if(event.getSource()==s19){
            row = 1;
            place = 9;
        }
        else if(event.getSource()==s110){
            row = 1;
            place = 10;
        }
        else if(event.getSource()==s21){
            row = 2;
            place = 1;
        }
        else if(event.getSource()==s22){
            row = 2;
            place = 2;
        }
        else if(event.getSource()==s23){
            row = 2;
            place = 3;
        }
        else if(event.getSource()==s24){
            row = 2;
            place = 4;
        }
        else if(event.getSource()==s25){
            row = 2;
            place = 5;
        }
        else if(event.getSource()==s26){
            row = 2;
            place = 6;
        }
        else if(event.getSource()==s27){
            row = 2;
            place = 7;
        }
        else if(event.getSource()==s28){
            row = 2;
            place = 8;
        }
        else if(event.getSource()==s29){
            row = 2;
            place = 9;
        }
        else if(event.getSource()==s210){
            row = 2;
            place = 10;
        }
        else if(event.getSource()==s31){
            row = 3;
            place = 1;
        }
        else if(event.getSource()==s32){
            row = 3;
            place = 2;
        }
        else if(event.getSource()==s33){
            row = 3;
            place = 3;
        }
        else if(event.getSource()==s34){
            row = 3;
            place = 4;
        }
        else if(event.getSource()==s35){
            row = 3;
            place = 5;
        }
        else if(event.getSource()==s36){
            row = 3;
            place = 6;
        }
        else if(event.getSource()==s37){
            row = 3;
            place = 7;
        }
        else if(event.getSource()==s38){
            row = 3;
            place = 8;
        }
        else if(event.getSource()==s39){
            row = 3;
            place = 9;
        }
        else if(event.getSource()==s310){
            row = 3;
            place = 10;
        }
        else if(event.getSource()==s41){
            row = 4;
            place = 1;
        }
        else if(event.getSource()==s42){
            row = 4;
            place = 2;
        }
        else if(event.getSource()==s43){
            row = 4;
            place = 3;
        }
        else if(event.getSource()==s44){
            row = 4;
            place = 4;
        }
        else if(event.getSource()==s45){
            row = 4;
            place = 5;
        }
        else if(event.getSource()==s46){
            row = 4;
            place = 6;
        }
        else if(event.getSource()==s47){
            row = 4;
            place = 7;
        }
        else if(event.getSource()==s48){
            row = 4;
            place = 8;
        }
        else if(event.getSource()==s49){
            row = 4;
            place = 9;
        }
        else if(event.getSource()==s410){
            row = 4;
            place = 10;
        }
        else if(event.getSource()==s51){
            row = 5;
            place = 1;
        }
        else if(event.getSource()==s52){
            row = 5;
            place = 2;
        }
        else if(event.getSource()==s53){
            row = 5;
            place = 3;
        }
        else if(event.getSource()==s54){
            row = 5;
            place = 4;
        }
        else if(event.getSource()==s55){
            row = 5;
            place = 5;
        }
        else if(event.getSource()==s56){
            row = 5;
            place = 6;
        }
        else if(event.getSource()==s57){
            row = 5;
            place = 7;
        }
        else if(event.getSource()==s58){
            row = 5;
            place = 8;
        }
        else if(event.getSource()==s59){
            row = 5;
            place = 9;
        }
        else if(event.getSource()==s510){
            row = 5;
            place = 10;
        }
        else if(event.getSource()==s61){
            row = 6;
            place = 1;
        }
        else if(event.getSource()==s62){
            row = 6;
            place = 2;
        }
        else if(event.getSource()==s63){
            row = 6;
            place = 3;
        }
        else if(event.getSource()==s64){
            row = 6;
            place = 4;
        }
        else if(event.getSource()==s65){
            row = 6;
            place = 5;
        }
        else if(event.getSource()==s66){
            row = 6;
            place = 6;
        }
        else if(event.getSource()==s67){
            row = 6;
            place = 7;
        }
        else if(event.getSource()==s68){
            row = 6;
            place = 8;
        }
        else if(event.getSource()==s69){
            row = 6;
            place = 9;
        }
        else if(event.getSource()==s610){
            row = 6;
            place = 10;
        }
        else if(event.getSource()==s71){
            row = 7;
            place = 1;
        }
        else if(event.getSource()==s72){
            row = 7;
            place = 2;
        }
        else if(event.getSource()==s73){
            row = 7;
            place = 3;
        }
        else if(event.getSource()==s74){
            row = 7;
            place = 4;
        }
        else if(event.getSource()==s75){
            row = 7;
            place = 5;
        }
        else if(event.getSource()==s76){
            row = 7;
            place = 6;
        }
        else if(event.getSource()==s77){
            row = 7;
            place = 7;
        }
        else if(event.getSource()==s78){
            row = 7;
            place = 8;
        }
        else if(event.getSource()==s79){
            row = 7;
            place = 9;
        }
        else if(event.getSource()==s710){
            row = 7;
            place = 10;
        }
        else if(event.getSource()==s81){
            row = 8;
            place = 1;
        }
        else if(event.getSource()==s82){
            row = 8;
            place = 2;
        }
        else if(event.getSource()==s83){
            row = 8;
            place = 3;
        }
        else if(event.getSource()==s84){
            row = 8;
            place = 4;
        }
        else if(event.getSource()==s85){
            row = 8;
            place = 5;
        }
        else if(event.getSource()==s86){
            row = 8;
            place = 6;
        }
        else if(event.getSource()==s87){
            row = 8;
            place = 7;
        }
        else if(event.getSource()==s88){
            row = 8;
            place = 8;
        }
        else if(event.getSource()==s89){
            row = 8;
            place = 9;
        }
        else if(event.getSource()==s810){
            row = 8;
            place = 10;
        }
        places.add(new Place(row,place));
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #624ED7;");
        button.setDisable(true);
    }

}


