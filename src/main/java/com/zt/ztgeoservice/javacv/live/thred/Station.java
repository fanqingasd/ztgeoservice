package com.zt.ztgeoservice.javacv.live.thred;

import com.zt.ztgeoservice.javacv.live.ConvertVideo;

public class Station extends Thread {
    private static Thread thread =new Thread();
    //定义退出标志，true一直执行，false退出循环
    public volatile boolean isRun = true;
    private String rtsp;
    private String rtmp;
    private String[] a;
    public  Station(String rtsp, String rtmp, String[] a)
    {
        this.rtsp = rtsp;
        this.rtmp = rtmp;
        this.a = a;
    }
 /*   @Override
        public void run () {
        while (isRun) {
            try {
                if (rtsp != null && rtmp != null) {
                    new ConvertVideo().from(rtsp)
                            .to(rtmp)
                            .go(a);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(
                        "当前线程：" + Thread.currentThread().getName() + " 当前任务：" + "停止...");

                e.printStackTrace();
            }
        }
            System.out.println("hello " + rtsp);
        }*/

/*    public static void threadRtmp(String[]ips, String a)throws Exception {
        if (ips!=null){
        for (String s : ips) {
            String rtsp = "rtsp://admin:abcd1234@" + s;
            int len = s.length();
            String rtmp = "rtmp://192.168.9.11/live/camera" + s.substring(len - 2);
            if (a!=null && a.equals("1")){
                thread = new Station(rtsp, rtmp,ips);
            }else {
                thread = new Station(rtsp, rtmp, null);
            }
            thread.start();
        }
        }
        }*/

}