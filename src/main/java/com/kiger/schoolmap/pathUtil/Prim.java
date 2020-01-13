package com.kiger.schoolmap.pathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小生成树
 */
public class Prim {
    EdgeWeightGraph graph;
    // 记录顶点是否访问
    private boolean[] marked;
    // 记录顶点到生成树的距离
    private double[] dis;

    public Prim(EdgeWeightGraph graph) {
        this.graph = graph;
    }

    private void init(int startId) {
        marked = new boolean[graph.adjSize];
        dis = new double[graph.adjSize];

        Arrays.fill(dis, Integer.MAX_VALUE);
        Vertex vertex = graph.adj[startId];
        for (Edge edge = vertex.firstEdge; edge != null; edge = edge.next) {
            dis[edge.id] = edge.weight;
        }
    }

    public List<Integer> primCal(int startId) {
        init(startId);

        List<Integer> mst = new ArrayList<>();
        int count = 0, minVertex = 0;
        double min = 0.0;
        marked[startId] = true;
        mst.add(startId);
        ++count;
        while (count < graph.V) {
            min = Integer.MAX_VALUE;
            for (int i = 1; i < dis.length; i++) {
                if (!marked[i] && dis[i] < min) {
                    min = dis[i];
                    minVertex = i;
                }
            }

            marked[minVertex] = true;
            mst.add(minVertex);
            ++count;

            Vertex vertex = graph.adj[minVertex];
            for (Edge edge = vertex.firstEdge; edge != null; edge = edge.next) {
                if (!marked[edge.id] && edge.weight < dis[edge.id])
                    dis[edge.id] = edge.weight;
            }
        }

        return mst;
    }

}
