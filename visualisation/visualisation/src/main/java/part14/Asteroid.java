package part14;

import java.util.Random;

import javafx.scene.shape.Polygon;

public class Asteroid extends Character {
    public Asteroid(int x, int y) {
        super(new PolygonCreater().createPolygon(), x, y);
        this.accelerate(new Random().nextDouble() * 2);
    }

}
