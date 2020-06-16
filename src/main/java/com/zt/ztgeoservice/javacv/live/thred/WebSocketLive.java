package com.zt.ztgeoservice.javacv.live.thred;


import com.zt.ztgeoservice.alarm.entity.Alarm;
import com.zt.ztgeoservice.alarm.service.AlarmService;
import com.zt.ztgeoservice.websocket.MyThread;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/webSocketLive")
@Component
public class WebSocketLive {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    public  static CopyOnWriteArraySet<WebSocketLive> webSocketSet = new CopyOnWriteArraySet<WebSocketLive>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        Alarm alarm=new Alarm();
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
       /* try {
            sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());
            onMessage("有新连接加入！当前在线人数为" + getOnlineCount(), session);

        } catch (IOException e) {
            System.out.println("IO异常");
        }*/
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for (WebSocketLive item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 异常推送
     * @param session session
     * @param error 异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送信息
     * @param message 信息
     * @throws IOException IO异常
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketLive item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketLive.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketLive.onlineCount--;
    }


}

