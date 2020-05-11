package com.zt.ztgeoservice.areas.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Areas {
    private Integer id;
    private String arName;
    private String arState;
    private String arType;
    @JSONField(format = "yyyy-MM-dd")
    private Date arCreationTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date arUpdateTime;
    private String arPosition;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getArState() {
        return arState;
    }

    public void setArState(String arState) {
        this.arState = arState;
    }

    public String getArType() {
        return arType;
    }

    public void setArType(String arType) {
        this.arType = arType;
    }

    public Date getArCreationTime() {
        return arCreationTime;
    }

    public void setArCreationTime(Date arCreationTime) {
        this.arCreationTime = arCreationTime;
    }

    public Date getArUpdateTime() {
        return arUpdateTime;
    }

    public void setArUpdateTime(Date arUpdateTime) {
        this.arUpdateTime = arUpdateTime;
    }

    public String getArPosition() {
        return arPosition;
    }

    public void setArPosition(String arPosition) {
        this.arPosition = arPosition;
    }
}
