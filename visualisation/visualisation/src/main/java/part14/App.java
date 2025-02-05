package part14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class App extends Application {
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    int counter = 0;
    int score = 0;

    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        HBox topBar = new HBox();
        Label points = new Label("Points: " + score);
        topBar.getChildren().add(points);

        Pane pane = new Pane();
        List<Asteroid> asteroidList = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        Map<KeyCode, Boolean> controlMap = new HashMap<>();
        pane.setPrefSize(WIDTH, HEIGHT);

        Ship ship = new Ship(WIDTH / 2, HEIGHT / 2);

        pane.getChildren().add(ship.getcharacterPolygon());

        createNewAsteroidSet(asteroidList, pane, 5);

        borderPane.setTop(topBar);
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane);

        scene.setOnKeyPressed(e -> {
            controlMap.put(e.getCode(), true);
        });
        scene.setOnKeyReleased(e -> {
            controlMap.put(e.getCode(), false);
        });

        new AnimationTimer() {
            public void handle(long now) {

                counter++;

                asteroidList.forEach(asteroid -> {
                    if (asteroid.isCollide(ship.getcharacterPolygon())) {
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
                if (controlMap.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {

                    Projectile projectile = new Projectile((int) ship.getcharacterPolygon().getTranslateX(),
                            (int) ship.getcharacterPolygon().getTranslateY());

                    projectile.getcharacterPolygon().setRotate(ship.getcharacterPolygon().getRotate());
                    projectile.accelerate(2);
                    projectiles.add(projectile);

                    pane.getChildren().add(projectile.getcharacterPolygon());
                }
                if (counter % 200 == 0) {
                    projectiles.forEach(projectile -> {
                        pane.getChildren().remove(projectile.getcharacterPolygon());
                    });
                    projectiles.clear();
                }
                if (asteroidList.isEmpty()) {
                    createNewAsteroidSet(asteroidList, pane, 5);
                }

                ship.move();
                forEachMove(asteroidList);
                forEachMove(projectiles);

                projectiles.forEach(projectile -> {

                    List<Asteroid> collidedList = asteroidList
                            .stream()
                            .filter(asteroid -> asteroid.isCollide(projectile.getcharacterPolygon()))
                            .collect(Collectors.toList());

                    collidedList.stream().forEach(collidedAsteroid -> {
                        score += 100;
                        points.setText("Points: " + score);
                        asteroidList.remove(collidedAsteroid);
                        pane.getChildren().remove(collidedAsteroid.getcharacterPolygon());
                    });
                });

            }
        }.start();

        stage.setTitle("Asteroids!");
        stage.setScene(scene);
        stage.show();
    }

    public void createNewAsteroidSet(List<Asteroid> asteroidList, Pane pane, int howMany) {

        if (asteroidList.isEmpty()) {

            Random random = new Random();

            for (int i = 0; i < howMany; i++) {

                asteroidList.add(new Asteroid(random.nextInt(WIDTH / 3), random.nextInt(HEIGHT)));
            }

            asteroidList.forEach(astro -> pane.getChildren().add(astro.getcharacterPolygon()));
        }
    }

    public <T extends Character> void forEachMove(List<T> list) {
        list.forEach(item -> item.move());

    }

}