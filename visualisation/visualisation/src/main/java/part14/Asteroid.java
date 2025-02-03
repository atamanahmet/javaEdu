package part14;

import java.util.Random;

import javafx.scene.robot.Robot;
import javafx.scene.shape.Polygon;

public class Asteroid extends Character {
    private double rotationalMovement;

    public Asteroid(int x, int y) {
        super(new PolygonCreater().createPolygon(), x, y);
        Random random = new Random();

        int number = random.nextInt(360);
        super.getcharacterPolygon().setRotate(number);
        System.out.println(number);

        int accelerationAmount = 1 + random.nextInt(10) * 5;
        for (int i = 0; i < accelerationAmount; i++) {
            super.accelerate();
        }

        this.rotationalMovement = 0.5 - random.nextDouble();
    }

    public void move() {
        super.move();
        super.getcharacterPolygon().setRotate(super.getcharacterPolygon().getRotate() + this.rotationalMovement);
    }

}
