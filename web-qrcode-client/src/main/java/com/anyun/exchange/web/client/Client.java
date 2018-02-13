package com.anyun.exchange.web.client;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Client {

    final static CountDownLatch messageLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:4567/qrcode";
            System.out.println("Connecting to " + uri);
            container.connectToServer(MyClientEndpoint.class, URI.create(uri));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
