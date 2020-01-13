package com.kiger.schoolmap.bean;

import java.io.Serializable;

/**
 * 景点JavaBean
 */
public class Spot implements Serializable {

    // 景点编号
    private Integer spotId;
    // 景点名称
    private String spotName;
    // 景点横坐标
    private double coordX;
    // 景点纵坐标
    private double coordY;
    // 景点信息
    private String spotInfo;
    // 连接景点数组
    private Coord[] coords;

    public Spot() {}

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public String getSpotInfo() {
        return spotInfo;
    }

    public void setSpotInfo(String spotInfo) {
        this.spotInfo = spotInfo;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public Coord[] getCoords() {
        return coords;
    }

    public void setCoords(int size) {
        this.coords = new Coord[size];
    }

    @Override
    public String toString() {
        return "Spot{" +
                "spotId=" + spotId +
                ", spotName='" + spotName + '\'' +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                ", spotInfo='" + spotInfo + '\'' +
                '}';
    }
}
