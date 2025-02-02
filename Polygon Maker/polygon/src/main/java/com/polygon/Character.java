package com.polygon;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

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

        this.characterPolygon.setTranslateX(this.characterPolygon.getTranslateX() + movement.getX());
        this.characterPolygon.setTranslateY(this.characterPolygon.getTranslateY() + movement.getY());
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.characterPolygon.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.characterPolygon.getRotate()));
        changeX = changeX * 0.05;
        changeY = changeY * 0.05;
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

}
