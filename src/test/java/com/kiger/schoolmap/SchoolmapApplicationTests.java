package com.kiger.schoolmap;

import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.mapper.PathMapper;
import com.kiger.schoolmap.mapper.SpotMapper;
import com.kiger.schoolmap.pathUtil.*;
import com.kiger.schoolmap.service.SpotService;
import com.kiger.schoolmap.serviceImpl.PathServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolmapApplicationTests {

    @Autowired
    SpotMapper spotMapper;
    @Autowired
    PathMapper pathMapper;
    @Autowired
    SpotService spotService;
    @Autowired
    PathServiceImpl pathService;

    @Test
    void contextLoads() {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);
        Dijkstra dijkstra = new Dijkstra(graph);
        List<Integer> list = dijkstra.dijkstraCal(1, 3);
        for (int id :
                list) {
            System.out.print(id + " ");
        }
    }

    @Test
    void test1() {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);
        DFS dfs = new DFS(graph);
        List<List<Integer>> lists = dfs.dfsPathsCal(1, 3);
        for (List<Integer> list :
                lists) {
            System.out.println(list);
        }
    }

    @Test
    void test2() {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);
        BFS bfs = new BFS(graph);
        List<Integer> list = bfs.bfsPathCal(1, 6);
        System.out.println(list);
    }

    @Test
    void test3() {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);
        Prim prim = new Prim(graph);
        List<Integer> list = prim.primCal(1);
        System.out.println(list);
    }
}
