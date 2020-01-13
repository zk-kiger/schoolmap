package com.kiger.schoolmap.pathUtil;

import java.util.*;

/**
 * Dijkstra算法
 */
public class Dijkstra {

    // 图
    EdgeWeightGraph graph;
    // 标记是否访问
    private boolean[] marked;
    // 记录起点到每个顶点之间的权值
    private double[] dis;
    // 记录最短路径
    private int[] path;

    public Dijkstra(EdgeWeightGraph graph) {
        this.graph = graph;
    }

    /**
     * 初始化
     *  更新起点到其他各点之间的距离
     */
    private void init(int startId) {
        this.marked = new boolean[graph.adjSize];
        this.dis = new double[graph.adjSize];
        this.path = new int[graph.adjSize];

        path[startId] = startId;
        dis[startId] = 0.0;
        for (Edge edge = graph.adj[startId].firstEdge; edge != null; edge = edge.next) {
            dis[edge.id] = edge.weight;
            path[edge.id] = startId;
        }
        for (int i = 0; i < dis.length; i++) {
            if (dis[i] == 0.0 && i != startId)
                dis[i] = Double.MAX_VALUE;
        }
    }

    /**
     * 计算最短路径
     */
    public List<Integer> dijkstraCal(int startId, int endId) {
        init(startId);
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                Double d1 = new Double(o1.weight);
                Double d2 = new Double(o2.weight);
                return d1.compareTo(d2);
            }
        });

        queue.add(new Edge(startId, 0.0, null));
        while (!queue.isEmpty()) {
            Edge now = queue.peek();
            marked[now.id] = true;
            queue.remove();

            for (Edge edge = graph.adj[now.id].firstEdge; edge != null; edge = edge.next) {
                Integer id = edge.id;
                if (!marked[id]) {
                    if (dis[id] > dis[now.id] + edge.weight) {
                        dis[id] = dis[now.id] + edge.weight;
                        path[id] = now.id;
                    }
                    queue.add(edge);
                }
            }
        }

        return pathTo(startId, endId);
    }

    private List<Integer> pathTo(int x, int y) {
        List<Integer> list = new ArrayList<>();
        list.add(y);
        int cur = y;
        while (path[cur] != x) {
            list.add(path[cur]);
            cur = path[cur];
        }
        list.add(x);
        Collections.reverse(list);
        return list;
    }

}
