package com.example.kursach_client1;

import connection.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import connection.Client;

public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 455);
        primaryStage.setTitle("cinema");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Connection.client = new Client("127.0.0.1",8080);
        launch(args);
    }
}