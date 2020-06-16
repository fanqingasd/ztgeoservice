package com.zt.ztgeoservice.javacv.python;

import com.zt.ztgeoservice.websocket.MyWebSocket;
import io.netty.util.internal.ConcurrentSet;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;

@Slf4j
@Component
public class MediaUtils {
    /**
     * 直播摄像机id集合，避免重复拉流
     */
    private static Set<Long> liveSet = new ConcurrentSet<>();
    /**
     * 用于构造回放rtsp地址
     */
    @Value("${myconfig.replay-rtsp}")
    private String rtspReplayPattern;
    /**
     * 视频帧率
     */
    public static int frameRate = 15;
    /**
     * 视频宽度
     */
    public static int frameWidth = 480;
    /**
     * 视频高度
     */
    public static int frameHeight = 270;

    /**
     * 摄像机直播
     * @param rtsp 摄像机直播地址
     * @param cameraName 摄像机名称
     * @param cameraId 摄像机id
     * @throws Exception e
     */
    @Async
    public void live(String rtsp, String cameraName, Long cameraId) throws Exception {
        if (liveSet.contains(cameraId)) {
            return;
        }
        liveSet.add(cameraId);
        FFmpegFrameGrabber grabber = createGrabber(rtsp);
        startCameraPush(grabber, cameraName, cameraId);
    }

    /**
     * 推送图片（摄像机直播）
     * @param grabber
     * @throws Exception
     */
    @Test
    @Async
    public void startCameraPush(FFmpegFrameGrabber grabber, String cameraName, Long cameraId) throws Exception {
        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
        try {
            grabber.start();
            int i = 1;
            while (liveSet.contains(cameraId)) {
                Frame frame = grabber.grabImage();
                if (null == frame) {
                    continue;
                }
                BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(frame);

                byte[] bytes = imageToBytes(bufferedImage, "jpg");

                //使用websocket发送图片数据
                //MyWebSocket.sendInfo(ByteBuffer.wrap(bytes), cameraId);
            }
        } finally {
            if (grabber != null) {
                grabber.stop();
            }
        }
    }
    /**
     * 构造视频抓取器
     * @param rtsp 拉流地址
     * @return
     */
    public FFmpegFrameGrabber createGrabber(String rtsp) {
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtsp);
        grabber.setOption("rtsp_transport","tcp");
        //设置帧率
        grabber.setFrameRate(frameRate);
        //设置获取的视频宽度
        grabber.setImageWidth(frameWidth);
        //设置获取的视频高度
        grabber.setImageHeight(frameHeight);
        //设置视频bit率
        grabber.setVideoBitrate(2000000);
        return grabber;
    }

    /**
     * 图片转字节数组
     * @param bImage 图片数据
     * @param format 格式
     * @return 图片字节码
     */
    private byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }





}
