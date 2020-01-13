package com.kiger.schoolmap.pathUtil;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 广度优先搜索
 */
public class BFS {

    EdgeWeightGraph graph;
    // 标记是否访问
    private boolean[] marked;
    // 记录路径
    private int[] path;

    public BFS(EdgeWeightGraph graph) {
        this.graph = graph;
    }

    private void init() {
        this.marked = new boolean[graph.adjSize];
        this.path = new int[graph.adjSize];
    }

    public List<Integer> bfsPathCal(int startId, int endId) {
        init();
        boolean flag = false;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        marked[startId] = true;
        queue.add(startId);

        while (!queue.isEmpty()) {
            int curVertex = queue.remove();
            Vertex vertex = graph.adj[curVertex];
            for (Edge edge = vertex.firstEdge; edge != null; edge = edge.next) {
                if (!marked[edge.id]) {
                    path[edge.id] = curVertex;
                    marked[edge.id] = true;
                    queue.add(edge.id);
                }
                if (edge.id == endId) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
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
