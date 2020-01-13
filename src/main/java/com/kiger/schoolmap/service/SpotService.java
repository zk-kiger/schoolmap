package com.kiger.schoolmap.service;

import com.kiger.schoolmap.bean.Spot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotService {

    List<Spot> querySpots();

    Spot querySpotById(Integer id);

}
