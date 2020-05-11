package com.zt.ztgeoservice.count.service.impl;

import com.zt.ztgeoservice.count.dao.CountDao;
import com.zt.ztgeoservice.count.entity.Count;
import com.zt.ztgeoservice.count.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountServiceImpl implements CountService{
    @Autowired
    private CountDao countDao;

    @Override
    public Long countCamera() {
        return countDao.countCamera();
    }

    @Override
    public Long countAlarm() {
        return countDao.countAlarm();
    }

    @Override
    public Long countPeople() {
        return countDao.countPeople();
    }

    @Override
    public Long countHouses() {
        return countDao.countHouses();
    }

    @Override
    public Long countParking() {
        return countDao.countParking();
    }

    @Override
    public Long countParkingrecord() {
        return countDao.countParkingrecord();
    }

    @Override
    public Long countPeoplerecord() {
        return countDao.countPeoplerecord();
    }

    @Override
    public List<Count> countCar(Count count) {
        return countDao.countCar(count);
    }
}
