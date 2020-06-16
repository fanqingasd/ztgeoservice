package com.zt.ztgeoservice.javacv.live;

import java.util.HashMap;
import java.util.Map;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import static org.bytedeco.ffmpeg.global.avcodec.*;


public class ConvertVideo extends Thread{
    /**
     * rtsp转rtmp（转封装方式）.
     */
    FFmpegFrameGrabber grabber=null;
    FFmpegFrameRecorder recorder=null;
    int width=-1,height=-1;
    private static int ip=0;//是否结束推流1为结束
    private static String [] getIpList=null;//是否结束推流1为结束
    private static Thread thread =new Thread();//创建线程
    private static Map map =new HashMap();
    private String rtsp;//rtsp ip地址
    private String rtmp;//rtmp ip地址
    // 视频参数
    protected int audiocodecid;
    protected int codecid;
    protected double framerate;// 帧率
    protected int bitrate;//比特率

    public  ConvertVideo(String rtsp, String rtmp)
    {
        this.rtsp = rtsp;
        this.rtmp = rtmp;
    }

    public ConvertVideo from(String src) throws Exception{
        grabber=new FFmpegFrameGrabber(src);
        if(src.indexOf("rtsp")>=0){
            grabber.setOption("rtsp_transport","tcp");
        }
        grabber.start();
        if(width<0||height<0){
            width=grabber.getImageWidth();
            height=grabber.getImageHeight();
        }
        //canvas.showImage(grabber.grab());
        /*视频参数*/
        audiocodecid=grabber.getAudioCodec();
        return this;

    }

    public ConvertVideo to(String out) throws Exception{
        recorder=new FFmpegFrameRecorder(out,width,height);
        recorder.setGopSize(2);
        recorder.setFrameRate(framerate);
        recorder.setVideoBitrate(bitrate);
        AVFormatContext fc=null;
        if(out.indexOf("rtmp")>=0||out.indexOf("flv")>=0){
            //封装flv
            recorder.setFormat("flv");
            recorder.setAudioCodecName("aac");
            fc=grabber.getFormatContext();
        }
        recorder.start(fc);
        return this;
    }

    public ConvertVideo go() throws org.bytedeco.javacv.FrameGrabber.Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        long err_index = 0;
            for (int no_frame_index = 0; no_frame_index <= 50 || err_index < 1; ) {
                AVPacket avpk = null;
                try {
                    if (ip==1){//如果ip=1那么就结束
                        ThreadGroup group = thread.currentThread().getThreadGroup();
                        Thread[] threads = new Thread[group.activeCount()];
                        group.enumerate(threads, false);
                        //for (String s:getIpList) {
                            //String iplist = "rtsp://admin:abcd1234@" + s;
                        for (Thread thread : threads) {
                                if (thread.getName().substring(0, 7).equals("Thread-") /*&& rtsp.equals(iplist)*/) {
                                    thread.interrupt();
                                }
                            }
                        }
                   // }
                    thread.sleep(1);
                     avpk = grabber.grabPacket();

                    if (avpk == null || avpk.size() <= 0 || avpk.data() == null) {
                        no_frame_index++;
                        continue;
                    }
                    err_index += (recorder.recordPacket(avpk) ? 0 : 1);
                    av_packet_unref(avpk);

                } catch (InterruptedException e) {//捕获异常，结束for循环
                    // 销毁构造器
                    grabber.close();
                    recorder.close();
                    System.err.println("设备中断推流成功...");
                    break;
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                    err_index++;
                } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
                    err_index++;
                }
            }
        grabber.close();
        recorder.close();
        System.err.println("设备推流完毕...");
        ip=0;//变更初始状态
        map.clear();//清除缓存
        return this;

    }
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public void run () {
            try {
                    if (rtsp != null && rtmp != null) {
                        from(rtsp).to(rtmp).go();
                    }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(
                        "当前线程：" + Thread.currentThread().getName() + " 当前任务：" + "停止...");
                e.printStackTrace();
            }
        System.err.println("GameOver-------"+Thread.currentThread().getName());
    }

    public static void threadRtmp(String[]ips,/*String[]ipList,*/ int a)throws Exception {
        if (a==1) {
            ip=1;
            //getIpList=ipList;
        } else {
            int j = 0;
            for (String s : ips) {
                String rtsp = "rtsp://admin:abcd1234@" + s;
                int len = s.length();
                String rtmp = "rtmp://192.168.9.11/live/camera" + s.substring(len - 2);
                thread = new ConvertVideo(rtsp, rtmp);
                thread.start();
                map.put("ip" + j++, s);
            }
        }
    }

    /*    public static Thread[] getThreads() {
        ThreadGroup group = thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[group.activeCount()];

            group.enumerate(threads, false);
            System.out.println("当前运行的线程有：");
            System.out.println("线程ID" + "\t线程名称");
            for (Thread thread : threads) {
                System.out.println(thread.getId() + "\t" + thread.getName());
            }

        return threads;
    }*/

    public static Map getMap() {
        return map;
    }
}
