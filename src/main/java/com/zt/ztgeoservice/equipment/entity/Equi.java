package com.zt.ztgeoservice.equipment.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 用户对象 sys_user
 *
 * @author
 */
public class Equi
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer oid;
    private Integer groupid;
    private String [] groupids;
    private Integer [] groupidArray;
    private String ipaddress;
    private String username;
    private String password;
    private String url;
    private String agreement;
    private Integer  type;
    private Integer pixel;
    private String model;
    private String factory;
    private  Integer loactionAilas;
    private Double  height;
    private  Double pitch;
    private Double yaw;
    private Double roll;
    private Double viewAngel;
    private  String loacttion;
    private Integer isShow;
    private Integer Port;
    private String groupname;
    private String name;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPixel() {
        return pixel;
    }

    public void setPixel(Integer pixel) {
        this.pixel = pixel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getLoactionAilas() {
        return loactionAilas;
    }

    public void setLoactionAilas(Integer loactionAilas) {
        this.loactionAilas = loactionAilas;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Double getYaw() {
        return yaw;
    }

    public void setYaw(Double yaw) {
        this.yaw = yaw;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getViewAngel() {
        return viewAngel;
    }

    public void setViewAngel(Double viewAngel) {
        this.viewAngel = viewAngel;
    }

    public String getLoacttion() {
        return loacttion;
    }

    public void setLoacttion(String loacttion) {
        this.loacttion = loacttion;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getPort() {
        return Port;
    }

    public void setPort(Integer Port) {
        this.Port = Port;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGroupids() {
        return groupids;
    }

    public void setGroupids(String[] groupids) {
        this.groupids = groupids;
    }

    public Integer[] getGroupidArray() {
        return groupidArray;
    }

    public void setGroupidArray(Integer[] groupidArray) {
        this.groupidArray = groupidArray;
    }
}
