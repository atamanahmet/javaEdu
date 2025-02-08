package com.invaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {
    public final int WIDTH = 600;
    public final int HEIGHT = 400;

    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage window) {
        Ship ship = new Ship(0, 0);
        Alien alien = new Alien(0, 0);

        alien.getPolygon().setScaleX(0.2);
        alien.getPolygon().setScaleY(0.2);
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.getChildren().add(ship.getPolygon());
        pane.getChildren().add(alien.getPolygon());
        Scene scene = new Scene(pane);

        window.setScene(scene);
        window.show();
    }
}