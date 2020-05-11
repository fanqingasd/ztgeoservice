package com.zt.ztgeoservice.equipment.controller;

import com.github.pagehelper.PageHelper;
import com.zt.ztgeoservice.equipment.service.EquiService;
import com.zt.ztgeoservice.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EquiController {
    @Autowired
   private EquiService equiService;
    @RequestMapping(value = "/queryEqui",method = RequestMethod.GET)//,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    public Object  queryEqui(PageRequest pageQuery,@RequestParam Integer pageSize,@RequestParam Integer pageNum) {
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNum(pageNum);
        return equiService.findPage(pageQuery);

    }
}