module com.polygon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.polygon to javafx.fxml;
    exports com.polygon;
}
