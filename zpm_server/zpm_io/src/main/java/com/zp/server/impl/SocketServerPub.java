package com.zp.server.impl;

import com.zp.server.ByteUtils;
import com.zp.server.inter.ServerProtcolPub;
import com.zp.server.model.ServerParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class SocketServerPub implements ServerProtcolPub {


    private ServerSocket server;

    @Autowired
    private ServerParams params;

    @Override
    public void start() {
        synchronized (this){
            try {
                server = new ServerSocket(Integer.parseInt(params.getPort()));
                while(true){
                    Socket socket = server.accept();
                    SocketSessionClient sessionClient = new SocketSessionClient(socket);
                    byte[] b = new byte[1024];
                    InputStream in = socket.getInputStream();
                    int len = 0;
                    ByteArrayOutputStream aos = new ByteArrayOutputStream();
                    while((len = in.read(b))>0){
                        aos.write(b,0,len);
                    }
                    byte[] bout = aos.toByteArray();
                    String rs = ByteUtils.bytesToHexString(bout);
                    File f = new File("/home/outFile");
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(rs.getBytes());
                    fo.close();
                    socket.close();
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
    public void setPools(ThreadPoolExecutor pools) {

    }
}
