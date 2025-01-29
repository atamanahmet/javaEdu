package part14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage window) {

        Pane pane = new Pane();
        pane.setPrefSize(600, 400);
        // Circle circle = new Circle(30, 50, 10);

        // pane.getChildren().add(circle);
        Polygon poly = new Polygon(0, 50, 60, 50, 30, 0);

        pane.getChildren().add(poly);
        poly.setLayoutY(300);
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                System.out.println(e);
                double a = poly.getTranslateX();
                poly.setTranslateX(a + 50);
            }
            if (e.getCode() == KeyCode.LEFT) {
                System.out.println(e);
                double a = poly.getTranslateX();
                poly.setTranslateX(a - 50);
            }

        });
    }
}

// 0 10 20 30 40 50
// 0
// 10
// 20
// 30
// 40
// 50