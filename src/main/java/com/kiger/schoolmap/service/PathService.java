package com.kiger.schoolmap.service;

import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.pathUtil.EdgeWeightGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PathService {

    List<Spot> queryMinPath(String startSpotName, String endSpotName);

    List<Path> getPaths();

    EdgeWeightGraph createGraph(List<Spot> spots, List<Path> paths);

    boolean addPath(String startSpotName, String endSpotName);

    List<List<Spot>> querySimplePaths(String startSpotName, String endSpotName);

    List<Spot> queryLessPath(String startSpotName, String endSpotName);

    List<Spot> queryBestPlan(String startSpotName);
}
