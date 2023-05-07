module com.example.kursach_client1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.base;
    requires lombok;
    requires java.sql;
    requires jdk.jfr;

/*    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires validatorfx;*/
    opens com.example.kursach_client1 to javafx.fxml;
    exports com.example.kursach_client1;
    exports com.example.kursach_client1.Controllers;
    opens com.example.kursach_client1.Controllers to javafx.fxml;
    exports com.example.kursach_client1.models;
    opens com.example.kursach_client1.models to javafx.fxml;
}