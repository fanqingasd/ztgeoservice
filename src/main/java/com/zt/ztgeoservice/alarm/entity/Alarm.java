package com.zt.ztgeoservice.alarm.entity;

import java.util.Date;

public class Alarm {
    private Integer id;
    private Date alTime;
    private String  alPlace;
    private String  alContent;
    private String  alType;
    private String  alStatus;
    private String  alDealresult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAlTime() {
        return alTime;
    }

    public void setAlTime(Date alTime) {
        this.alTime = alTime;
    }

    public String getAlPlace() {
        return alPlace;
    }

    public void setAlPlace(String alPlace) {
        this.alPlace = alPlace;
    }

    public String getAlContent() {
        return alContent;
    }

    public void setAlContent(String alContent) {
        this.alContent = alContent;
    }

    public String getAlType() {
        return alType;
    }

    public void setAlType(String alType) {
        this.alType = alType;
    }

    public String getAlStatus() {
        return alStatus;
    }

    public void setAlStatus(String alStatus) {
        this.alStatus = alStatus;
    }

    public String getAlDealresult() {
        return alDealresult;
    }

    public void setAlDealresult(String alDealresult) {
        this.alDealresult = alDealresult;
    }
}
