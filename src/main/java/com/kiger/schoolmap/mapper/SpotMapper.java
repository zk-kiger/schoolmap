package com.kiger.schoolmap.mapper;

import com.kiger.schoolmap.bean.Spot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SpotMapper {

    @Select("select * from spot where spotName=#{spotName}")
    Spot querySpotByName(String spotName);

    @Select("select * from spot")
    List<Spot> querySpots();

    @Select("select * from spot where spotId=#{spotId}")
    Spot querySpotById(int spotId);
}
