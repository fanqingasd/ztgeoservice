package com.zt.ztgeoservice.count.controller;

import com.zt.ztgeoservice.count.dao.CountDao;
import com.zt.ztgeoservice.count.entity.Count;
import com.zt.ztgeoservice.count.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CountController {
    @Autowired
    private CountService countService;

    /**
     * 一标六实统计
     * @param count
     * @return
     */
    @RequestMapping(value = "/count/first")
    public Count count(Count count){
        Long people=countService.countPeople();
        Long peoplerecord=countService.countPeoplerecord();
        Long houses=countService.countHouses();
        Long parking=countService.countParking();
        Long parkingrecord=countService.countParkingrecord();
        Long camera=countService.countCamera();
        Long alarm=countService.countAlarm();
            count.setCountPeople(people);
            count.setCountPeoplerecord(peoplerecord);
            count.setCountHouses(houses);
            count.setCountParking(parking);
            count.setCountParkingrecord(parkingrecord);
            count.setCountCamera(camera);
            count.setCountAlarm(alarm);
        return count;
    }

    /**
     * 车辆进出时间段统计
     * @param count
     * @return
     */
    @RequestMapping(value = "/count/countCar")
    public List countParkingrecord(Count count) throws ParseException {
        Date date=new Date();
        SimpleDateFormat startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd 23:00:00");

        String datetime = startTime.format(new java.util.Date());
        String datetime2 = endTime.format(new java.util.Date());

        /*count.setStartTime(datetime);
        count.setEndTime(datetime2);*/
        List<Count>list=countService.countCar(count);
        return list;
    }




}
