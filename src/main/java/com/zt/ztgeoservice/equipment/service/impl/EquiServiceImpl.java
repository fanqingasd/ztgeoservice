package com.zt.ztgeoservice.equipment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.ztgeoservice.equipment.dao.EquiDao;
import com.zt.ztgeoservice.equipment.entity.Equi;
import com.zt.ztgeoservice.equipment.service.EquiService;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;
import com.zt.ztgeoservice.util.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquiServiceImpl implements EquiService {
    @Autowired
    private EquiDao equiDao;
   /* @Override
    public List<Equi> queryEqui(){
        return equiDao.queryEqui();
    }*/

    @Override
    public PageResult findPage(PageRequest pageRequest) {
         return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }


    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<Equi> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Equi> queryEqui = equiDao.queryEqui();
        return new PageInfo<Equi>(queryEqui);
    }
}
