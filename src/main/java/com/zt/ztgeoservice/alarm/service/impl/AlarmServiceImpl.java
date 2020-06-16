package com.zt.ztgeoservice.alarm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.ztgeoservice.alarm.dao.AlarmDao;
import com.zt.ztgeoservice.alarm.entity.Alarm;
import com.zt.ztgeoservice.alarm.service.AlarmService;
import com.zt.ztgeoservice.areas.entity.Areas;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;
import com.zt.ztgeoservice.util.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {
    @Autowired
    private AlarmDao alarmDao;
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    @Override
    public Alarm getalarm(Alarm alarm) {
        return alarmDao.getalarm();
    }


    private PageInfo<Alarm> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Alarm> queryAlarm = alarmDao.queryAlarm();
        return new PageInfo<Alarm>(queryAlarm);
    }
}
