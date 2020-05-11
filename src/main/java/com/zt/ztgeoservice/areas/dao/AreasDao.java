package com.zt.ztgeoservice.areas.dao;

import com.zt.ztgeoservice.areas.entity.Areas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AreasDao {
    public List<Areas> queryAreas();
}
