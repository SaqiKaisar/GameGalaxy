module com.example.gamegalaxy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.gamegalaxy to javafx.fxml;
    exports com.example.gamegalaxy;
}