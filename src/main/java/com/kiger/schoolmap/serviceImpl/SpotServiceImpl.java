package com.kiger.schoolmap.serviceImpl;

import com.kiger.schoolmap.bean.Coord;
import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;
import com.kiger.schoolmap.mapper.PathMapper;
import com.kiger.schoolmap.mapper.SpotMapper;
import com.kiger.schoolmap.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotMapper spotMapper;
    @Autowired
    PathMapper pathMapper;
    @Autowired
    SpotService spotService;

    /**
     * 查询景点
     *  将该景点为起点的路经的终点坐标封装为Coord存储到 coords[] 数组中
     */
    @Cacheable(cacheNames = "spots")
    @Override
    public List<Spot> querySpots() {
        List<Spot> spotList = spotMapper.querySpots();

        for (Spot spot :
                spotList) {
            List<Path> nextSpotsList = pathMapper.queryPathsByStartId(spot.getSpotId());
            spot.setCoords(nextSpotsList.size());
            Coord[] coords = spot.getCoords();
            int i = 0;
            for (Path path :
                    nextSpotsList) {
                Spot spot1 = spotService.querySpotById(path.getEndSpotId());
                coords[i++] = new Coord(spot1.getCoordX(), spot1.getCoordY());
            }
        }

        return spotList;
    }

    /**
     * 根据编号查询景点
     */
    @Cacheable(cacheNames = "spot")
    @Override
    public Spot querySpotById(Integer id) {
        return spotMapper.querySpotById(id);
    }


}
