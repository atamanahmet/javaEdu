package part14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        List<Asteroid> asteroidList = new ArrayList<>();
        Map<KeyCode, Boolean> controlMap = new HashMap<>();
        pane.setPrefSize(600, 400);

        // Polygon ship = new Polygon(-5, -5, 10, 0, -5, 5);
        // ship.setTranslateX(300);
        // ship.setTranslateY(200);

        Ship ship = new Ship(300, 200);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            asteroidList.add(new Asteroid(random.nextInt(300), random.nextInt(400)));
        }
        asteroidList.forEach(item -> {
            item.getcharacterPolygon().setRotate(random.nextDouble() * 360);
            item.accelerate(0.5);
        });

        // Asteroid asteroid = new Asteroid(20, 20);
        // Projectile projectile = new Projectile((int)
        // ship.getcharacterPolygon().getTranslateX() - 10,(int)
        // ship.getcharacterPolygon().getTranslateY() - 5);

        // pane.getChildren().add(asteroid.getcharacterPolygon());

        pane.getChildren().add(ship.getcharacterPolygon());
        asteroidList.forEach(astro -> pane.getChildren().add(astro.getcharacterPolygon()));
        // pane.getChildren().add(projectile.getcharacterPolygon());

        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(e -> {
            controlMap.put(e.getCode(), true);
        });
        scene.setOnKeyReleased(e -> {
            controlMap.put(e.getCode(), false);
        });
        // asteroid.turnRight();
        // asteroid.turnRight();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();
        // asteroid.accelerate();

        new AnimationTimer() {
            public void handle(long now) {
                asteroidList.forEach(item -> {
                    if (isCollide(ship.getcharacterPolygon(), item.getcharacterPolygon())) {
                        stop();
                    }
                });
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
                    ship.accelerate(0.05);
                }
                if (controlMap.getOrDefault(KeyCode.W, false)) {
                    ship.accelerate(0.05);
                }
                if (controlMap.getOrDefault(KeyCode.DOWN, false)) {
                    ship.stop();
                }
                // if (controlMap.getOrDefault(KeyCode.SPACE, false)) {
                // System.out.println("asd");
                // projectile.setX(ship.getcharacterPolygon().getTranslateX() - 10);
                // projectile.setY(ship.getcharacterPolygon().getTranslateY() - 5);
                // projectile.getcharacterPolygon().setRotate(ship.getcharacterPolygon().getRotate());
                // projectile.accelerate();

                // }
                // if (controlMap.getOrDefault(KeyCode.S, false)) {
                // ship.stop();
                // if (isCollide(true)) {
                // this.stop();
                // }

                // }
                ship.move();
                asteroidList.forEach(item -> item.move());
                // projectile.move();

            }
        }.start();
        System.out.println(ship.getcharacterPolygon().getTranslateX());

        stage.setTitle("Asteroids!");
        stage.setScene(scene);
        stage.show();
    }

    public boolean isCollide(Polygon object1, Polygon object2) {
        Shape collision = Shape.intersect(object1, object2);
        return collision.getBoundsInLocal().getWidth() != -1;
    }
}