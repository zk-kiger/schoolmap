package com.kiger.schoolmap.bean;

import java.io.Serializable;

/**
 * 路径JavaBean
 */
public class Path implements Serializable {

    // 路径编号
    private Integer pathId;
    // 路径起点
    private Integer startSpotId;
    // 路径终点
    private Integer endSpotId;
    // 路径长度
    private Double pathLength;

    public Path() {}

    public Path(Integer startSpotId, Integer endSpotId, Double pathLength) {
        this.startSpotId = startSpotId;
        this.endSpotId = endSpotId;
        this.pathLength = pathLength;
    }

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Integer getStartSpotId() {
        return startSpotId;
    }

    public void setStartSpotId(Integer startSpotId) {
        this.startSpotId = startSpotId;
    }

    public Integer getEndSpotId() {
        return endSpotId;
    }

    public void setEndSpotId(Integer endSpotId) {
        this.endSpotId = endSpotId;
    }

    public Double getPathLength() {
        return pathLength;
    }

    public void setPathLength(Double pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public String toString() {
        return "Path{" +
                "pathId=" + pathId +
                ", startSpotId=" + startSpotId +
                ", endSpotId=" + endSpotId +
                ", pathLength=" + pathLength +
                '}';
    }
}
