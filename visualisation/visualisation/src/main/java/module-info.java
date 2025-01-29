module part14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens part14 to javafx.fxml;

    exports part14;
}
