package com.zt.ztgeoservice.javacv;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.python.core.*;
import org.python.util.PythonInterpreter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;


public class test {

    /**
     2      * 按帧录制视频
     3      *
     4      * @param inputFile-该地址可以是网络直播/录播地址，也可以是远程/本地文件路径
     5      * @param outputFile
     6      *            -该地址只能是文件地址，如果使用该方法推送流媒体服务器会报错，原因是没有设置编码格式
     7      * @throws FrameGrabber.Exception
     8      * @throws FrameRecorder.Exception
     9      * @throws org.bytedeco.javacv.FrameRecorder.Exception

     10
     /*      String fileName = videoFramesPath + "/img_" + String.valueOf(flag) + ".jpg";
     //文件储存对象
     File outPut = new File(fileName);
     //获取帧
     frame = grabber.grabImage();
     if (frame != null) {
     ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
     }
     flag++;**/
       /*        // 1. Python面向函数式编程: 在Java中调用Python函数
                    // String pythonFunc = "E:\\pythonWorkSpace\\demo01\\ZTSmart\\facerecognition\\face_recognition_class.py";
                    String[] aaa = new String[] { "python", "E:\\pythonWorkSpace\\demo01\\ZTSmart\\facerecognition\\python_test.py",fileName};

                   // String[] cmdArr = new String[]{"cmd", "/C", pythonFunc, fileName};
                    Process process = Runtime.getRuntime().exec(aaa);
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line=null;
                    if ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    in.close();
                    int result = process.waitFor();
                    System.out.println("结果" + result);*/
    //视频帧图片存储路径
    public static String videoFramesPath = "E:/IdeaTool/img";
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

    public static void frameRecord(String inputFile, String outputFile, int audioChannel) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        Frame frame = null;
        //标识
        int flag = 0;
        boolean isStart = true;// 该变量建议设置为全局控制变量，用于控制录制结束
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        grabber.start();
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        grabber.setOption("rtsp_transport", "tcp");
        //设置帧率
        grabber.setFrameRate(frameRate);
        //设置获取的视频宽度
        grabber.setImageWidth(frameWidth);
        //设置获取的视频高度
        grabber.setImageHeight(frameHeight);
        //设置视频bit率
        grabber.setVideoBitrate(2000000);
        try {
            while (true) {
                frame = grabber.grabImage();
                if (frame != null) {
                    if (!canvas.isDisplayable()) {//窗口是否关闭
                        grabber.stop();//停止抓取
                        System.exit(2);//退出
                        break;
                    }
                    // 截取的帧图片
                    Java2DFrameConverter converter = new Java2DFrameConverter();
                    BufferedImage srcImage = converter.getBufferedImage(frame);
                    int srcImageWidth = srcImage.getWidth();
                    int srcImageHeight = srcImage.getHeight();

                    // 对截图进行等比例缩放(缩略图)
                    int width = 480;
                    int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
                    BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                    thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

                    String fileName = videoFramesPath + "/img_" + String.valueOf(flag) + ".jpg";
                    //文件储存对象
                    File outPut = new File(fileName);
                    ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
         /*------------------------------------------------------------------------------------------------------------*/
                   /* Properties props = new Properties();
                    props.put("python.home","E:\\jython\\Lib");
                    props.put("python.console.encoding", "UTF-8");
                    props.put("python.security.respectJavaAccessibility", "false");
                    props.put("python.import.site","false");
                    Properties preprops = System.getProperties();
                    PythonInterpreter.initialize(preprops, props, new String[0]);

                    // 2. 面向对象式编程: 在Java中调用Python对象实例的方法
                    String pythonClass = "E:\\pythonWorkSpace\\demo01\\ZTSmart\\facerecognition\\face_recognition_class.py";
                    //String pythonClass = "E:\\face_recognition_class.py";
                    // python对象名
                    String pythonObjName = "cal";
                    // python类名
                    String pythonClazzName = "FaceRecognition";

                    PythonInterpreter pi2 = new PythonInterpreter();
                    *//*PySystemState sys = Py.getSystemState();
                    sys.path.add("sys.path.append('E:\\jython");*//*

                    pi2.exec("import sys");
                    pi2.exec("print sys.path");
                    pi2.exec("sys.path.append('C:\\jython2.7.0\\Lib')");
                    pi2.exec("sys.path.append('C:\\jython2.7.0\\Lib\\site-packages')");
                    pi2.exec("sys.path.append('C:\\jython2.7.0\\Lib\\site-packages\\cv2')");


                    // 加载python程序
                    pi2.execfile(pythonClass);
                    // 实例化python对象
                    pi2.exec(pythonObjName + "=" + pythonClazzName + "()");
                    // 获取实例化的python对象
                    PyObject pyObj = pi2.get(pythonObjName);
                    // 调用python对象方法,传递参数并接收返回值
                    PyObject result = pyObj.invoke("power", new PyObject[] {Py.newString(fileName), Py.newInteger(1)});
                    double power = Py.py2double(result);
                    System.out.println(power);
                    pi2.cleanup();
                    pi2.close();*/
                }
                flag++;
                canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
                Thread.sleep(200);
            }
            grabber.stop();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }

    public static void main(String[] args) throws Exception {
                  String inputFile = "rtsp://admin:abcd1234@192.168.10.65:554/cam/realmonitor?channel=1&subtype=0";
                  String outputFile = "recorde.mp4";
                  frameRecord(inputFile, outputFile,1);
/*        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");
        props.put("haha",fileName);
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interp = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        sys.path.add("F:\\work\\ztgeoservice\\src\\main\\java\\com\\zt\\ztgeoservice\\javacv\\python\\lib");
        interp.exec("import sys");
        interp.exec("print sys.path");
        interp.exec("path = \"F:\\work\\ztgeoservice\\src\\main\\java\\com\\zt\\ztgeoservice\\javacv\\python\\lib\"");
        interp.exec("sys.path.append(path)");
        interp.exec("print sys.path");
        String ret = "";
        String pyfilePath = "E:\\pythonWorkSpace\\demo01\\ZTSmart\\facerecognition\\face_recognition_class.py";
        interp.execfile(pyfilePath);
        PyFunction func = (PyFunction)interp.get("keywordsRouter",PyFunction.class);*/

                    /*PyObject pyobj = func.__call__(new PyString("传给keywordsRouter方法的参数"));
                    ret = pyobj.toString();//这里ret可能会乱码
                    String newStr = null;  //通过new String(ret.getBytes("iso8859-1"), "utf-8")转一下就好了
                        newStr = new String(ret.getBytes("iso8859-1"), "utf-8");
                    System.out.println("anwser= " +newStr);  //newStr就不会乱码了*/

    }

}
