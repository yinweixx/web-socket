package com.anyun.exchange.web.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebSocket
public class WebQrcodeSocket {

    public static List<Session> sessionList = new ArrayList<>();


    @OnWebSocketConnect
    public void connected(Session session) {
        sessionList.add(session);
        System.out.println("a client join WebSocket");
    }

    @OnWebSocketClose
    public void closed(int statusCode, String reason) {
        System.out.println("session close");
    }

    @OnWebSocketMessage
    public void message(String message) throws IOException {
        sessionList.stream().filter(Session::isOpen).forEach(session->{
            try {
                session.getRemote().sendString(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
