package com.zp.server.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
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
        byte[] b = new byte[1024];
        String rmsg = null;
        int len =0;
        try {
            ByteOutputStream bos = new ByteOutputStream();
            while ((len = in.read(b)) > 0) {
                bos.write(b,0,len);
            }
            rmsg = new String(bos.getBytes());
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return rmsg;
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
