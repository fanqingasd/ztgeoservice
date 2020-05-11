package com.zt.ztgeoservice.equipment.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.io.Serializable;
import java.util.Date;

public class Equi implements Serializable {
private Integer id;
private String eqName;
    @JSONField(format = "yyyy-MM-dd")
    private Date eqCreationTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date eqUpdateTime;
    private String eqState;
    private String eqType;
    private String eqHAngle;
    private String eqZAngle;
    private String eqViewAngle;
    private  String geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public Date getEqCreationTime() {
        return eqCreationTime;
    }

    public void setEqCreationTime(Date eqCreationTime) {
        this.eqCreationTime = eqCreationTime;
    }

    public Date getEqUpdateTime() {
        return eqUpdateTime;
    }

    public void setEqUpdateTime(Date eqUpdateTime) {
        this.eqUpdateTime = eqUpdateTime;
    }

    public String getEqState() {
        return eqState;
    }

    public void setEqState(String eqState) {
        this.eqState = eqState;
    }

    public String getEqType() {
        return eqType;
    }

    public void setEqType(String eqType) {
        this.eqType = eqType;
    }

    public String getEqHAngle() {
        return eqHAngle;
    }

    public void setEqHAngle(String eqHAngle) {
        this.eqHAngle = eqHAngle;
    }

    public String getEqZAngle() {
        return eqZAngle;
    }

    public void setEqZAngle(String eqZAngle) {
        this.eqZAngle = eqZAngle;
    }

    public String getEqViewAngle() {
        return eqViewAngle;
    }

    public void setEqViewAngle(String eqViewAngle) {
        this.eqViewAngle = eqViewAngle;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
 /*   private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };*/
}

