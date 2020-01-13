package com.kiger.schoolmap.servlet;

import com.kiger.schoolmap.bean.DealResult;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpotServlet {

    @Autowired
    SpotService spotService;

    @GetMapping("/querySpotInfo")
    public DealResult querySpotInfo() {
        DealResult dealResult = new DealResult();
        try {
            List<Spot> spotList = spotService.querySpots();
            if (spotList != null) {
                dealResult.setSucceed(true);
                dealResult.setData(spotList);
            }
        } catch (Exception e) {
            dealResult.setSucceed(false);
            dealResult.setResultInfo("查询景点信息失败!");
        }
        return dealResult;
    }

    @GetMapping("/querySpot")
    public Spot querySpotById(Integer id) {
        return spotService.querySpotById(id);
    }

}
