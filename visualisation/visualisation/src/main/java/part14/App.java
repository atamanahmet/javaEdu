package part14;

import java.security.Key;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        Polygon ship = new Polygon(-5, -5, 10, 0, -5, 5);
        ship.setTranslateX(300);
        ship.setTranslateY(200);

        pane.getChildren().add(ship);

        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                ship.setRotate(ship.getRotate() - 5);
            }
            if (e.getCode() == KeyCode.RIGHT) {
                ship.setRotate(ship.getRotate() + 5);
            }
        });
        stage.setTitle("Asteroids!");
        stage.setScene(scene);
        stage.show();
    }
}