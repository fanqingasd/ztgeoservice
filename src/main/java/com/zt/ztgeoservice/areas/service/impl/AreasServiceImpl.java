package com.zt.ztgeoservice.areas.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.ztgeoservice.areas.dao.AreasDao;
import com.zt.ztgeoservice.areas.entity.Areas;
import com.zt.ztgeoservice.areas.service.AreasService;
import com.zt.ztgeoservice.equipment.entity.Equi;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;
import com.zt.ztgeoservice.util.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasServiceImpl implements AreasService{
    @Autowired
    private AreasDao areasDao;
  /*  @Override
    public List<Areas> queryAreas(){
        return areasDao.queryAreas();
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
    private PageInfo<Areas> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Areas> queryAreas = areasDao.queryAreas();
        return new PageInfo<Areas>(queryAreas);
    }

}
