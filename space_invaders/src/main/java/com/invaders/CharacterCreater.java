package com.invaders;

import javafx.scene.shape.Polygon;

public abstract class CharacterCreater {
    private Polygon polygon;

    public CharacterCreater(Polygon polygon, int x, int y) {
        this.polygon = polygon;
        this.polygon.setTranslateX(x);
        this.polygon.setTranslateY(y);
    }

    public Polygon getPolygon() {
        return this.polygon;
    }

}
