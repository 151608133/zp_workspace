package com.zp.server.io.create.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.zp.server.io.create.absClazz.SessionClient;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class SocketSessionClient extends SessionClient<Socket> {


    SocketSessionClient(Socket socket){
        super(socket);
        setSessionId();
    }

    @Override
    protected String getSessionId() {
        InputStream in = getInputStream();
        int len =0;
        String phoneNum = "";

        try {
            ByteOutputStream bos = new ByteOutputStream();
            byte[] b = new byte[10];
            in.read(b,0,10);

            b=new byte[2];
            in.read(b,0,2);
            int length = b[1];

            b=new byte[length];
            in.read(b,0,len);

            for (int i=2;i<(2+11);i++){
                Integer index = new Integer(b[i]);
                phoneNum+=index;
            }
            log.info("sim number:"+phoneNum);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return phoneNum;
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

    @Override
    public void close() {
        try {
            getInputStream().close();
            getOutputStream().close();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

}
