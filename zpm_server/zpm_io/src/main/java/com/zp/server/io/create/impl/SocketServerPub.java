package com.zp.server.io.create.impl;

import com.zp.server.io.connmanage.inter.ClientManager;
import com.zp.server.io.create.inter.RequestHandler;
import com.zp.server.io.create.inter.ServerProtcolPub;
import com.zp.server.model.ServerParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@Slf4j
public class SocketServerPub implements ServerProtcolPub {


    private ServerSocket server;

    @Autowired
    private ServerParams params;

    @Autowired
    @Qualifier(value = "ClientsManagerPools")
    private ClientManager manager;

    @Override
    public void start() {
        if(server!=null){
            return;
        }
        synchronized (this){
            try {
                log.info("socket server start port{}",params.getPort());
                server = new ServerSocket(Integer.parseInt(params.getPort()));
                while(true){
                    Socket socket = server.accept();
                    log.info("receive socket client connections !");
                    SocketSessionClient sessionClient = new SocketSessionClient(socket);
                    if(sessionClient.getSessionId()==null){
                        continue;
                    }
                    RequestHandler handler = new SocketHandler(sessionClient);
                    setClientToPool(sessionClient.getSessionId(), handler);
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
    }

    @Override
    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void setClientToPool(String sessionId,RequestHandler handler) {
        manager.submit(sessionId,handler);
    }

}
