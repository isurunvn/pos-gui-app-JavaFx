module com.example.boardingexpensetracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boardingexpensetracker to javafx.fxml;
    exports com.example.boardingexpensetracker;
}