package com.example.kursach_client1.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.kursach_client1.models.SceneSwitcher;
import connection.Connection;
import entity.CastList;
import entity.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StatisticReturnController {

    @FXML
    private PieChart diagram;

    @FXML
    private Button exitId1;

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
        Connection.client.sendObject(8);
        Object obj = Connection.client.readObject();
        List<Film> films = CastList.castList(obj, Film.class);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getKol_return() > 0) pieChartData.add(new PieChart.Data(films.get(i).getName(), films.get(i).getKol_return()));
        }
        diagram.setData(pieChartData);
    }

}
