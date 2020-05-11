package com.zt.ztgeoservice.areas.controller;

import com.zt.ztgeoservice.areas.entity.Areas;
import com.zt.ztgeoservice.areas.service.AreasService;
import com.zt.ztgeoservice.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreasController {
    @Autowired
    private AreasService areasService;

    @RequestMapping(value = "/areas/queryAreas")
    public Object queryAreas(PageRequest pageQuery){
   return  areasService.findPage(pageQuery);
    }
}
