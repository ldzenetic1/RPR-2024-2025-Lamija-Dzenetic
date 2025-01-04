module com.example.lv9z1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.lv9z1 to javafx.fxml;
    exports com.example.lv9z1;
}