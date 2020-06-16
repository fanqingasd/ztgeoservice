package com.zt.ztgeoservice.javacv.live.thred;

import com.zt.ztgeoservice.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;

public class MainClass {
    @Autowired
    private AlarmService alarmService;
    /**
     *
     * */
  /* public static void threadRtmp(String[]ips)throws Exception {
       Thread thread=new Thread();

       //thread.interrupt();
               //thread.join();
      // thread.sleep(1);
       for (String s : ips) {
                String rtsp = "rtsp://admin:abcd1234@" + s;
                int len = s.length();
                String rtmp = "rtmp://192.168.9.11/live/camera" + s.substring(len - 2);
                thread = new Station(rtsp, rtmp);
                thread.start();
            }
    }*/
    public static void main(String[] args) {
      /*  String []ids={"192.168.10.25","192.168.10.11","192.168.10.65","192.168.10.26","192.168.10.27","192.168.10.28","192.168.10.29",
                "192.168.10.30","192.168.10.31","192.168.10.32","192.168.10.33","192.168.10.34","192.168.10.35","192.168.10.36","192.168.10.37"};*/
        //threadRtmp(null);
    }

}