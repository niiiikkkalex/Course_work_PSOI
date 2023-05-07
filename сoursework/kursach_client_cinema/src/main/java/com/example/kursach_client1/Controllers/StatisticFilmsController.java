package com.example.kursach_client1.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Film;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class StatisticFilmsController {

    @FXML
    private Button exitId1;

    ObservableList<Film> filmsList;
    @FXML
    private BarChart<String, Number> statistic;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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
        Connection.client.sendObject(7);
        Object obj = Connection.client.readObject();
        List<Film> films = CastList.castList(obj, Film.class);
        //xAxis.setLabel("Фильмы");
        yAxis.setLabel("Кол-во проданных билетов");

        XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();

        for(int i = 0; i < films.size(); i++){
            series.getData().add(new XYChart.Data<>(films.get(i).getName(), films.get(i).getKol_tickets()));
        }
        statistic.getData().add(series);
    }

}
