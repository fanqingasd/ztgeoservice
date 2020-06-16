package com.zt.ztgeoservice.user.controller;

import com.zt.ztgeoservice.user.entity.User;
import com.zt.ztgeoservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("Login.html")
    public String returnLogin(){
        return "Login";
    }
    @RequestMapping("Index.html")
    public String returnIndex(){
        return "Index";
    }
    //@PostMapping("/login")
    //@ResponseBody
    @RequestMapping(value="/login")
    public String login(HttpServletRequest req, HttpServletResponse res, User user, Model model){
        try{
            User u=userService.userlogin(req,res,user);
            if(u==null){
                model.addAttribute("error","用户名或密码不匹配");
                return "Login";
            }
            return "redirect:Index.html";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","用户名或密码不匹配");
            return "Login";
        }
    }
    @GetMapping("info")
    @ResponseBody
    public Map getUserInfo(HttpServletRequest req,HttpServletResponse res){
        Map<Object,Object>map=new HashMap<>();
        try {
            User us=userService.getUserByToken(res,req);
            if(us!=null){
                map.put("success",true);
            }else {
                map.put("success",false);
            }
            return map;
        } catch (Exception e) {
            map.put("success","根据token获取用户信息失败");
            return map;
        }
    }


    @RequestMapping(value="/img")
    public String img(){

            return "img";

    }
    @RequestMapping(value="/send")
    public String send(){

        return "send";

    }
}

