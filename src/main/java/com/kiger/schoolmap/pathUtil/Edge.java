package com.kiger.schoolmap.pathUtil;

import java.io.Serializable;

public class Edge implements Serializable {

    // 与顶点连接的边对应的顶点编号
    Integer id;
    // 与顶点之间的权值
    Double weight;
    // 邻接表的下一条边
    Edge next;

    public Edge() {}

    public Edge(Integer id, Double weight, Edge next) {
        this.id = id;
        this.weight = weight;
        this.next = next;
    }
}
