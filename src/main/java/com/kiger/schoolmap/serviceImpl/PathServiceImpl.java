package com.kiger.schoolmap.serviceImpl;

import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.mapper.PathMapper;
import com.kiger.schoolmap.mapper.SpotMapper;
import com.kiger.schoolmap.pathUtil.*;
import com.kiger.schoolmap.service.PathService;
import com.kiger.schoolmap.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PathServiceImpl implements PathService {

    @Autowired
    SpotMapper spotMapper;
    @Autowired
    PathMapper pathMapper;
    @Autowired
    SpotService spotService;
    @Autowired
    PathService pathService;

    /**
     * 查询最短路径
     *  1.读取数据库路径，创建地图信息(可以直接读取缓存)
     *  2.调用Dijkstra算法计算最短路径，并返回最短路径景点编号集合
     *  3.根据编号查询景点信息，并返回景点集合
     */
    @Override
    public List<Spot> queryMinPath(String startSpotName, String endSpotName) {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);

        Dijkstra dijkstra = new Dijkstra(graph);
        Integer startId = spotMapper.querySpotByName(startSpotName).getSpotId();
        Integer endId = spotMapper.querySpotByName(endSpotName).getSpotId();

        List<Spot> spotList = new ArrayList<>();
        List<Integer> spotIdList = dijkstra.dijkstraCal(startId, endId);
        for (int spotId : spotIdList
                ) {
            spotList.add(spotService.querySpotById(spotId));
        }

        return spotList;
    }
    // 获取所有路径 - 添加缓存
    @Override
    @Cacheable(cacheNames = "paths")
    public List<Path> getPaths() {
        return pathMapper.queryPaths();
    }
    // 创建地图 - 添加缓存
    @Override
    @Cacheable(cacheNames = "graph")
    public EdgeWeightGraph createGraph(List<Spot> spots, List<Path> paths) {
        return new EdgeWeightGraph(spots, paths);
    }

    /**
     * 添加路径
     *  需要更新地图缓存
     */
    @Override
    @CacheEvict(cacheNames = {"graph", "paths"})
    public boolean addPath(String startSpotName, String endSpotName) {
        Spot startSpot = spotMapper.querySpotByName(startSpotName);
        Spot endSpot = spotMapper.querySpotByName(endSpotName);

        if (startSpot == null || endSpot == null)
            return false;

        Path path_ = pathMapper.queryPath(startSpot.getSpotId(), endSpot.getSpotId());
        if (path_ != null)
            return false;

        Double startSpotCoordX = startSpot.getCoordX();
        Double startSpotCoordY = startSpot.getCoordY();
        Double endSpotCoordX = endSpot.getCoordX();
        Double endSpotCoordY = endSpot.getCoordY();
        Double pathLength = Math.sqrt(Math.pow(startSpotCoordX - endSpotCoordX, 2)
                + Math.pow(startSpotCoordY - endSpotCoordY, 2));
        Path path = new Path(startSpot.getSpotId(), endSpot.getSpotId(), pathLength);

        pathMapper.addPath(path);
        return true;
    }

    /**
     * 查询简单路径(DFS)
     *  使用DFS查询简单路径
     */
    @Override
    public List<List<Spot>> querySimplePaths(String startSpotName, String endSpotName) {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);

        Integer startId = spotMapper.querySpotByName(startSpotName).getSpotId();
        Integer endId = spotMapper.querySpotByName(endSpotName).getSpotId();
        List<List<Integer>> pathsList = new DFS(graph).dfsPathsCal(startId, endId);

        List<List<Spot>> lists = new ArrayList<>();
        for (List<Integer> list1 :
                pathsList) {
            List<Spot> spotList = new ArrayList<>();
            for (Integer id :
                 list1) {
                spotList.add(spotService.querySpotById(id));
            }
            lists.add(spotList);
        }

        return lists;
    }

    /**
     * 转机最少路径(BFS)
     *  路径中经过景点个数最少
     */
    @Override
    public List<Spot> queryLessPath(String startSpotName, String endSpotName) {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);

        Integer startId = spotMapper.querySpotByName(startSpotName).getSpotId();
        Integer endId = spotMapper.querySpotByName(endSpotName).getSpotId();
        List<Integer> list = new BFS(graph).bfsPathCal(startId, endId);

        List<Spot> spotList = new ArrayList<>();
        for (int spotId : list
        ) {
            spotList.add(spotService.querySpotById(spotId));
        }

        return spotList;
    }

    /**
     * 最佳布网方案(Prim)
     *  最小生成树
     */
    @Override
    public List<Spot> queryBestPlan(String startSpotName) {
        List<Spot> spots = spotService.querySpots();
        List<Path> paths = pathService.getPaths();
        EdgeWeightGraph graph = pathService.createGraph(spots, paths);

        Integer startId = spotMapper.querySpotByName(startSpotName).getSpotId();
        List<Integer> list = new Prim(graph).primCal(startId);

        List<Spot> spotList = new ArrayList<>();
        for (int spotId : list
        ) {
            spotList.add(spotService.querySpotById(spotId));
        }

        return spotList;
    }

}
