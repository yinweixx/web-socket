package com.anyun.exchange.web.controller;

import com.anyun.exchange.web.socket.WebQrcodeSocket;

import static spark.Spark.*;

public class WebSocketController {

    public static void main(String[] args) {
        webSocket("/qrcode", WebQrcodeSocket.class);
        init();
    }
}
