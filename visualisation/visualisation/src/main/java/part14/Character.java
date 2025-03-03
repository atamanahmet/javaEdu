package part14;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Character {

    private Polygon characterPolygon;
    private Point2D movement;

    public Character(Polygon polygon, int x, int y) {
        this.characterPolygon = polygon;
        this.characterPolygon.setTranslateX(x);
        this.characterPolygon.setTranslateY(y);
        this.movement = new Point2D(0, 0);

    }

    public void turnLeft() {
        this.characterPolygon.setRotate(this.characterPolygon.getRotate() - 5);
    }

    public void turnRight() {
        this.characterPolygon.setRotate(this.characterPolygon.getRotate() + 5);
    }

    public void move() {
        double widthHalft = this.getcharacterPolygon().getBoundsInParent().getMaxX();
        double heightHalft = this.getcharacterPolygon().getBoundsInParent().getMaxY();
        this.characterPolygon.setTranslateX(this.characterPolygon.getTranslateX() + this.movement.getX());
        this.characterPolygon.setTranslateY(this.characterPolygon.getTranslateY() + this.movement.getY());
        if (this.characterPolygon.getTranslateX() < 0) {
            this.characterPolygon.setTranslateX(this.characterPolygon.getTranslateX() +
                    App.WIDTH);
        }
        if (this.characterPolygon.getTranslateX() > App.WIDTH) {
            this.characterPolygon.setTranslateX(0);
        }

        if (this.characterPolygon.getTranslateY() < 0) {
            this.characterPolygon.setTranslateY(this.getcharacterPolygon().getTranslateY()
                    + App.HEIGHT);
        }
        if (this.characterPolygon.getTranslateY() > App.HEIGHT) {
            this.characterPolygon.setTranslateY(0);
        }

    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.characterPolygon.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.characterPolygon.getRotate()));
        changeX = changeX * 0.05;
        changeY = changeY * 0.05;
        this.movement = this.movement.add(changeX, changeY);
    }

    public void accelerate(double velocity) {
        double changeX = Math.cos(Math.toRadians(this.characterPolygon.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.characterPolygon.getRotate()));
        changeX = changeX * velocity;
        changeY = changeY * velocity;
        this.movement = this.movement.add(changeX, changeY);
    }

    // public void decelerate() {
    // double changeX = Math.cos(Math.toRadians(this.characterPolygon.getRotate()));
    // double changeY = Math.sin(Math.toRadians(this.characterPolygon.getRotate()));
    // changeX = changeX * -0.05;
    // changeY = changeY * -0.05;
    // this.movement = this.movement.add(changeX, changeY);
    // }

    public void setX(double xPos) {
        this.characterPolygon.setTranslateX(xPos);
    }

    public void setY(double yPos) {
        this.characterPolygon.setTranslateY(yPos);
    }

    public Polygon getcharacterPolygon() {
        return this.characterPolygon;
    }

    public void stop() {
        this.movement = new Point2D(0, 0);
    }

    public boolean isCollide(Polygon object2) {
        Shape collision = Shape.intersect(this.characterPolygon, object2);
        return collision.getBoundsInLocal().getWidth() != -1;
    }

}
