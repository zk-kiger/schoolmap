package com.kiger.schoolmap.pathUtil;

import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;

import java.io.Serializable;
import java.util.List;

/**
 * 边带权无向图
 */
public class EdgeWeightGraph implements Serializable {
    // 邻接表
    Vertex[] adj;
    // 顶点个数
    Integer V;
    // 边个数
    Integer E;
    // 邻接表大小
    Integer adjSize;

    public EdgeWeightGraph() {}

    /**
     * 在初始化邻接表时需要创建编号最大景点+1的空间
     * @param spots
     * @param paths
     */
    public EdgeWeightGraph(List<Spot> spots, List<Path> paths) {
        this.V = spots.size();
        this.E = paths.size();

        Spot lastSpot = spots.get(V-1);
        this.adjSize = lastSpot.getSpotId()+1;
        this.adj = new Vertex[adjSize];
        for (Spot spot : spots
             ) {
            Integer spotId = spot.getSpotId();
            adj[spotId] = new Vertex(spotId);
        }

        for (Path path : paths
                ) {
            addEdge(path.getStartSpotId(), path.getEndSpotId(), path.getPathLength());
        }
    }
    private void addEdge(int v, int w, double weight) {
        adj[v].add(new Edge(w, weight, null));
        adj[w].add(new Edge(v, weight, null));
    }

    public Vertex[] getAdj() {
        return adj;
    }

    public void setAdj(Vertex[] adj) {
        this.adj = adj;
    }

    public Integer getV() {
        return V;
    }

    public void setV(Integer v) {
        V = v;
    }

    public Integer getE() {
        return E;
    }

    public void setE(Integer e) {
        E = e;
    }

    public Integer getAdjSize() {
        return adjSize;
    }

    public void setAdjSize(Integer adjSize) {
        this.adjSize = adjSize;
    }
}
