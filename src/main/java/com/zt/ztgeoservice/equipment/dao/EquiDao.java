package com.zt.ztgeoservice.equipment.dao;

import com.zt.ztgeoservice.equipment.entity.Equi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface EquiDao{
    public List<Equi>queryEqui();
}
