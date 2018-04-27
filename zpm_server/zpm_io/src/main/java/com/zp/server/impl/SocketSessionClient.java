package com.zp.server.impl;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class SocketSessionClient extends SessionClient<Socket> {


    SocketSessionClient(Socket socket){
        super(socket);
    }


    @Override
    public InputStream getInputStream() {
        try {
            return getT().getInputStream();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return getT().getOutputStream();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String getURl() {
        try {
            return getT().getChannel().getRemoteAddress().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String getRemoteUrl() {
        try {
            return getT().getChannel().getRemoteAddress().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public boolean isAlive() {
        try {
            return getT().getKeepAlive();
        }catch  (Exception e){
            log.error(e.getMessage(),e);
        }
        return false;
    }

    @Override
    public String getRemoteIpAddress() {
        try {
            return getT().getChannel().getRemoteAddress().toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
