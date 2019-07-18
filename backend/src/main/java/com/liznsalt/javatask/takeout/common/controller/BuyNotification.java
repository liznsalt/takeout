package com.liznsalt.javatask.takeout.common.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/buyNotification")
public class BuyNotification {
    //用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<BuyNotification> user = new CopyOnWriteArraySet<BuyNotification>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //群发消息
        for (BuyNotification buyNotification : user) {
            buyNotification.session.getBasicRemote().sendText(message);
            //myWebSocket.session.getBasicRemote().sendText("<img src=''/>");
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " open...");
        this.session = session;
        user.add(this);
    }

    @OnClose
    public void onClose() {
        System.out.println(this.session.getId() + " close...");
        user.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(this.session.getId() + " error...");
        error.printStackTrace();
    }
}
