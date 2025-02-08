package com.invaders;

import javafx.scene.shape.Polygon;

public class Ship extends CharacterCreater {
    private Polygon shipPolygon;

    public Ship(int x, int y) {
        super(new Polygon(
                300 + 0.50 * 40, 200 + 0.06 * 40, // (50% 56%)
                300 + 0.75 * 40, 200 + 0.24 * 40, // (75% 74%)
                300 + 0.82 * 40, 200 + 0.50 * 40, // (82% 100%)
                300 + 0.18 * 40, 200 + 0.50 * 40, // (18% 100%)
                300 + 0.24 * 40, 200 + 0.23 * 40 // (24% 73%)
        ), x, y);
    }

}
