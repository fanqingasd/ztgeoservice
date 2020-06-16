package com.zt.ztgeoservice.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/webSocketServer")
@Component
public class WebSocketServer {
    //存放websocket 的线程安全的无序的集合
    private Session session;
    private  static  CopyOnWriteArraySet<WebSocketServer> websocket = new CopyOnWriteArraySet<WebSocketServer>();
    public static CopyOnWriteArraySet<WebSocketServer> getWebsocket() {
        return websocket;
    }
    public static void setWebsocket(CopyOnWriteArraySet<WebSocketServer> websocket) {
        WebSocketServer.websocket = websocket;
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    @OnOpen
    public void onOpen(Session session) {
       /* this.session = session;
        websocket.add(this); //加入set中*/
        System.out.println("有新连接加入！当前在线人数为" );
        try {
            sendMessage("有新连接加入！当前在线人数为" );
            onMessage("有新连接加入！当前在线人数为" , session);
        } catch (IOException e) {
            System.out.println("IO异常");
        }
        System.out.println("新开启了一个webSocket连接" + session.getId());
    }

    @OnMessage
    public void  onMessage(String message, Session session) {
        //这里的逻辑是客户端发送来消息服务器转发给所有在线客户端
        try {

            for (Session c:session.getOpenSessions()
                    ) {
                if(message!=null) {
                    c.getBasicRemote().sendText("一条异常警告信息");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("收到客户端发送的信息:"+message);
        System.out.println("当前的sessionId:"+session.getId());
        //return "SUCCESS";
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("webSocket连接关闭：sessionId:"+session.getId() + "关闭原因是："+reason.getReasonPhrase() + "code:"+reason.getCloseCode());
    }


    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    /**
     * 给客户端推送信息
     */
    public void sendMessage(String message) throws IOException {
        System.out.println("进入sendMessage方法");
        this.session.getBasicRemote().sendText(message);
    }

}
