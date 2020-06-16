package com.zt.ztgeoservice.websocket;

import com.zt.ztgeoservice.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Autowired
    public void setMessageService(AlarmService alarmService){
        MyWebSocket.alarmService=alarmService;

    }

}

