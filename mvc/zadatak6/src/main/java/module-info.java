module com.example.zadatak6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zadatak6 to javafx.fxml;
    exports com.example.zadatak6;
}