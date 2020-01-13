package com.kiger.schoolmap.servlet;

import com.kiger.schoolmap.bean.DealResult;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.service.PathService;
import com.kiger.schoolmap.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PathServlet {

    @Autowired
    SpotService spotService;
    @Autowired
    PathService pathService;

    /**
     * 最佳布网方案(Prim)
     *  最小生成树
     */
    @PostMapping("/queryBestPlan")
    public DealResult queryBestPlan(@RequestParam("startSpotName") String startSpotName) {
        DealResult dealResult = new DealResult();
        try {
            List<Spot> pathList = pathService.queryBestPlan(startSpotName);
            dealResult.setSucceed(true);
            dealResult.setData(pathList);
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("查询失败!");
        }
        return dealResult;
    }


    /**
     * 最少路径(BFS)
     *  起点到终点路径中经过的景点数量最少
     */
    @PostMapping("/queryLessPath")
    public DealResult queryLessPath(@RequestParam("startSpotName") String startSpotName,
                                    @RequestParam("endSpotName") String endSpotName) {
        DealResult dealResult = new DealResult();
        try {
            List<Spot> pathList = pathService.queryLessPath(startSpotName, endSpotName);
            dealResult.setSucceed(true);
            dealResult.setData(pathList);
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("查询失败!");
        }
        return dealResult;
    }

    /**
     * 简单路径(DFS)
     *  起点到终点的所有路径
     */
    @PostMapping("/querySimplePaths")
    public DealResult querySimplePaths(@RequestParam("startSpotName") String startSpotName,
                                       @RequestParam("endSpotName") String endSpotName) {
        DealResult dealResult = new DealResult();
        try {
            List<List<Spot>> pathsList = pathService.querySimplePaths(startSpotName, endSpotName);
            dealResult.setSucceed(true);
            dealResult.setData(pathsList);
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("查询失败!");
        }
        return dealResult;
    }

    /**
     * 查询最短路径(Dijkstra)
     */
    @PostMapping("/queryMinPath")
    public DealResult queryMinPath(@RequestParam("startSpotName") String startSpotName,
                                   @RequestParam("endSpotName") String endSpotName) {
        DealResult dealResult = new DealResult();
        try {
            List<Spot> spotList = pathService.queryMinPath(startSpotName, endSpotName);
            dealResult.setData(spotList);
            dealResult.setSucceed(true);
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("查询失败!");
        }
        return dealResult;
    }

    /**
     * 增加路径
     */
    @PostMapping("/addPath")
    public DealResult addPath(@RequestParam("startSpotName") String startSpotName,
                              @RequestParam("endSpotName") String endSpotName) {
        DealResult dealResult = new DealResult();
        try {
            boolean flag = pathService.addPath(startSpotName, endSpotName);
            if (flag) {
                dealResult.setSucceed(true);
                dealResult.setData(flag);
                dealResult.setResultInfo("添加路径成功!");
            }
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("添加路径失败!");
        }
        return dealResult;
    }
}
