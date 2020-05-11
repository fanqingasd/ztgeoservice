package com.zt.ztgeoservice.areas.service;

import com.zt.ztgeoservice.areas.entity.Areas;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;

import java.util.List;

public interface AreasService {
    /*public List<Areas> queryAreas();*/
    PageResult findPage(PageRequest pageRequest);
}
