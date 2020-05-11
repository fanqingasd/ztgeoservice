package com.zt.ztgeoservice.alarm.dao;

import com.zt.ztgeoservice.alarm.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AlarmDao {
   public List<Alarm>queryAlarm();
}
