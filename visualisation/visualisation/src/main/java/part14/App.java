package part14;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        Map<KeyCode, Boolean> controlMap = new HashMap<>();
        pane.setPrefSize(600, 400);

        // Polygon ship = new Polygon(-5, -5, 10, 0, -5, 5);
        // ship.setTranslateX(300);
        // ship.setTranslateY(200);

        Ship ship = new Ship(300, 200);
        Asteroid asteroid = new Asteroid(20, 20);
        Projectile projectile = new Projectile((int) ship.getcharacterPolygon().getTranslateX(),
                (int) ship.getcharacterPolygon().getTranslateY(), null);

        pane.getChildren().add(asteroid.getcharacterPolygon());

        pane.getChildren().add(ship.getcharacterPolygon());

        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(e -> {
            controlMap.put(e.getCode(), true);
        });
        scene.setOnKeyReleased(e -> {
            controlMap.put(e.getCode(), false);
        });
        asteroid.turnRight();
        asteroid.turnRight();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();

        new AnimationTimer() {
            public void handle(long now) {

                if (ship.getcharacterPolygon().getTranslateX() > 600) {
                    ship.setX(0);
                }
                if (ship.getcharacterPolygon().getTranslateY() > 400) {
                    ship.setY(0);
                }
                if (ship.getcharacterPolygon().getTranslateX() < 0) {
                    ship.setX(600);
                }
                if (ship.getcharacterPolygon().getTranslateY() < 0) {
                    ship.setY(400);
                }

                if (controlMap.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (controlMap.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();

                }
                if (controlMap.getOrDefault(KeyCode.A, false)) {
                    ship.turnLeft();
                }
                if (controlMap.getOrDefault(KeyCode.D, false)) {
                    ship.turnRight();

                }
                if (controlMap.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                if (controlMap.getOrDefault(KeyCode.W, false)) {
                    ship.accelerate();
                }
                if (controlMap.getOrDefault(KeyCode.DOWN, false)) {
                    ship.stop();
                }
                // if (controlMap.getOrDefault(KeyCode.S, false)) {
                // ship.stop();
                // if (isCollide(true)) {
                // this.stop();
                // }

                // }
                ship.move();
                asteroid.move();

            }
        }.start();
        System.out.println(ship.getcharacterPolygon().getTranslateX());

        stage.setTitle("Asteroids!");
        stage.setScene(scene);
        stage.show();
    }

    public boolean isCollide(boolean bool) {
        return bool;
    }
}