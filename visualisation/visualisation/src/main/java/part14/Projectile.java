package part14;

import javafx.scene.shape.Polygon;

public class Projectile extends Character {

    public Projectile(int x, int y) {
        super(new Polygon(5, 5, 15, 5, 15, 7, 5, 7), x, y);
    }
}
