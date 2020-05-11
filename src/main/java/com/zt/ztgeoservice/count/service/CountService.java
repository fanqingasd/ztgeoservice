package com.zt.ztgeoservice.count.service;

import com.zt.ztgeoservice.count.entity.Count;

import java.util.List;

public interface CountService {
    public Long countCamera();
    public Long countAlarm();
    public Long countPeople();
    public Long countHouses();
    public Long countParking();
    public Long countParkingrecord();
    public Long countPeoplerecord();
    public List<Count> countCar(Count count);
}
