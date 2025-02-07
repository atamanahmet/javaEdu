module com.invaders {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.invaders to javafx.fxml;
    exports com.invaders;
}
