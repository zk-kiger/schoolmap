package com.kiger.schoolmap.mapper;

import com.kiger.schoolmap.bean.Path;
import com.kiger.schoolmap.bean.Spot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PathMapper {

    @Insert("insert into path(startSpotId, endSpotId, pathLength) values(#{startSpotId}, #{endSpotId}, #{pathLength})")
    void addPath(Path path);

    @Select("select * from path where startSpotId=#{startSpotId} and endSpotId=#{endSpotId}")
    Path queryPath(Integer startSpotId, Integer endSpotId);

    @Select("select * from path")
    List<Path> queryPaths();

    @Select("select * from path where startSpotId=#{spotId}")
    List<Path> queryPathsByStartId(Integer spotId);
}
