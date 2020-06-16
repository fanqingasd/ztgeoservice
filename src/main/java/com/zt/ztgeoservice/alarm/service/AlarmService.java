package com.zt.ztgeoservice.alarm.service;

import com.zt.ztgeoservice.alarm.entity.Alarm;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;

import java.util.List;

public interface AlarmService {
    PageResult findPage(PageRequest pageRequest);
     Alarm getalarm(Alarm alarm);
}
