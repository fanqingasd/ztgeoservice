package com.zt.ztgeoservice.alarm.controller;

import com.zt.ztgeoservice.alarm.entity.Alarm;
import com.zt.ztgeoservice.alarm.service.AlarmService;
import com.zt.ztgeoservice.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "/alarm/queryAlarm")
    public Object queryAlarm(PageRequest pageQuery) {
        return alarmService.findPage(pageQuery);
    }

    @RequestMapping(value = "/alarm/getalarm")
    public Object getalarm(Alarm alarm) {
        return alarmService.getalarm(alarm);
    }
}