package com.zt.ztgeoservice.websocket;

import com.zt.ztgeoservice.alarm.dao.AlarmDao;
import com.zt.ztgeoservice.alarm.entity.Alarm;
import com.zt.ztgeoservice.alarm.service.AlarmService;
import com.zt.ztgeoservice.util.page.PageRequest;
import com.zt.ztgeoservice.util.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

public class MyThread implements  Runnable{
    @Autowired
    private  AlarmService alarmService;
    private Alarm alarm;
    private int new_sum;
    private boolean stopMe = true;
    public void stopMe() {
        stopMe = false;
    }
    //@Override
    public void run() {
        Alarm list=new Alarm();

        MyWebSocket wbs=new MyWebSocket();

               /* wbs.onMessage("dvx");
                wbs.onMessage("大概多少");*/

            CopyOnWriteArraySet<WebSocketServer> websocket = WebSocketServer.getWebsocket();
            for (WebSocketServer myWebSocket : websocket) {
                try {
                    myWebSocket.sendMessage("我要发消息了"+ Math.random());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    }
