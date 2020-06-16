package com.zt.ztgeoservice.equipment.controller;

import com.zt.ztgeoservice.equipment.service.EquiService;
import com.zt.ztgeoservice.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.zt.ztgeoservice.javacv.live.ConvertVideo.threadRtmp;
import static com.zt.ztgeoservice.javacv.live.ConvertVideo.getMap;


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

    /**
     * 开启视频流
     * @param ips
     * @throws Exception
     */
    @GetMapping(value = "/getIps")
    public  void  getIps(@RequestParam  String [] ips)throws Exception
    {
        threadRtmp(ips,0);
    }

    /**
     * 结束视频流
     * @throws Exception
     */
    @GetMapping(value = "/spotIps")
    public  void  spotIps(/*@RequestParam  String [] ipList*/)throws Exception
    {
        threadRtmp(null,1);
    }

    /**
     * 获取ip集合
     * @param
     * @throws Exception
     */
    @GetMapping(value = "/getMap")
    public Map getMapIp(Map map)throws Exception
    {
       map=getMap();
       return map;
    }

    @GetMapping("/demo3")
    public void demo3(HttpServletRequest request) {
        System.out.println(request.getHeader("myHeader"));
        for (Cookie cookie : request.getCookies()) {
            if ("myCookie".equals(cookie.getName())) {
                System.out.println(cookie.getValue());
            }
        }
    }

}