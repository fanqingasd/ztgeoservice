package com.zt.ztgeoservice.alarm.service;

import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;

public interface AlarmService {
    PageResult findPage(PageRequest pageRequest);
}
