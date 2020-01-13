package com.kiger.schoolmap.bean;

import java.io.Serializable;

/**
 * 景点坐标类
 */
public class Coord implements Serializable {

    private double x;
    private double y;

    public Coord() {
    }

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
