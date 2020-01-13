package com.kiger.schoolmap.pathUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先搜索
 */
public class DFS {

    EdgeWeightGraph graph;
    // 标记是否访问
    private boolean[] marked;
    // 保存所有路径集合
    List<List<Integer>> pathList;

    public DFS(EdgeWeightGraph graph) {
        this.graph = graph;
    }

    private void init() {
        this.marked = new boolean[graph.adjSize];
        pathList = new ArrayList<>();
    }

    /**
     * 查找简单路径
     */
    public List<List<Integer>> dfsPathsCal(int startId, int endId) {
        init();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        dfs(startId, endId, stack);
        return pathList;
    }
    private void dfs(int start, int end, ArrayDeque<Integer> stack) {
        marked[start] = true;
        stack.push(start);

        Vertex vertex = graph.adj[start];
        Edge edge;
        for (edge = vertex.firstEdge; edge != null; edge = edge.next) {
            if (start == end) {
                List<Integer> l = new ArrayList<>();
                ArrayDeque<Integer> clone = stack.clone();
                int len = clone.size();
                for (int i = 0; i < len; i++) {
                    l.add(clone.removeLast());
                }
                pathList.add(l);
                stack.pop();
                marked[start] = false;
                break;
            }
            if (!marked[edge.id])
                dfs(edge.id, end, stack);
        }
        if (vertex.firstEdge == null || edge == null) {
            stack.pop();
            marked[start] = false;
        }
    }

}
