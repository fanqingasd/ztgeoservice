package com.zt.ztgeoservice.count.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Count {
    private Long countPeople;
    private Long countHouses;
    private Long countParking;
    private Long countParkingrecord;
    private Long countCamera;
    private Long countAlarm;
    private Long countPeoplerecord;
    private Long mycount;
    private Long myhour;
   /* private String startTime;
    private String endTime;*/
   @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
   @JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
   private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date endTime;

    public Long getCountPeoplerecord() {
        return countPeoplerecord;
    }

    public void setCountPeoplerecord(Long countPeoplerecord) {
        this.countPeoplerecord = countPeoplerecord;
    }

    public Long getCountCamera() {
        return countCamera;
    }

    public void setCountCamera(Long countCamera) {
        this.countCamera = countCamera;
    }

    public Long getCountAlarm() {
        return countAlarm;
    }

    public void setCountAlarm(Long countAlarm) {
        this.countAlarm = countAlarm;
    }

    public Long getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(Long countPeople) {
        this.countPeople = countPeople;
    }

    public Long getCountHouses() {
        return countHouses;
    }

    public void setCountHouses(Long countHouses) {
        this.countHouses = countHouses;
    }

    public Long getCountParking() {
        return countParking;
    }

    public void setCountParking(Long countParking) {
        this.countParking = countParking;
    }

    public Long getCountParkingrecord() {
        return countParkingrecord;
    }

    public void setCountParkingrecord(Long countParkingrecord) {
        this.countParkingrecord = countParkingrecord;
    }

    public Long getMycount() {
        return mycount;
    }

    public void setMycount(Long mycount) {
        this.mycount = mycount;
    }

    public Long getMyhour() {
        return myhour;
    }

    public void setMyhour(Long myhour) {
        this.myhour = myhour;
    }

/*    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }*/

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
