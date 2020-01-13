package com.kiger.schoolmap.pathUtil;


import java.io.Serializable;

public class Vertex implements Serializable {
    // 顶点编号
    Integer vertexId;
    // 第一条边
    Edge firstEdge;
    // 出度个数
    public int size;

    public Vertex() {}

    public Vertex(Integer vertexId) {
        this.vertexId = vertexId;
    }

    public void add(Edge item) {
        if (firstEdge == null) {
            firstEdge = item;
        } else {
            Edge curr = firstEdge;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = item;
        }
        size++;
    }

}
