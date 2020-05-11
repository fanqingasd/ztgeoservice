package com.zt.ztgeoservice.count.dao;

import com.zt.ztgeoservice.count.entity.Count;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CountDao {
    public Long countCamera();
    public Long countAlarm();
    public Long countPeople();
    public Long countHouses();
    public Long countParking();
    public Long countParkingrecord();
    public Long countPeoplerecord();
    public List<Count>countCar(Count count);
}
