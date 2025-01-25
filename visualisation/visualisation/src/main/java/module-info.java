module part14 {
    requires javafx.controls;
    requires javafx.fxml;

    opens part14 to javafx.fxml;
    exports part14;
}
