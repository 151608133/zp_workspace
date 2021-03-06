package com.zp.server.io.create.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.zp.server.io.create.absClazz.SessionClient;
import com.zp.server.utils.ByteUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@Slf4j
public class SocketSessionClient extends SessionClient<Socket> {


    SocketSessionClient(Socket socket){
        super(socket);
        setSessionId();
    }

    @Override
    protected String createSessionId() {
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

            log.info("length value {} ,request ip {}" ,length,getT().getInetAddress().getHostAddress());

            if (length <= 0) {
                return null;
            }

            b=new byte[length];
            in.read(b,0,length);

            phoneNum = ByteUtils.convertHexToString(ByteUtils.bytesToHexString(Arrays.copyOfRange(b,3,14)));

            log.info("sim number {} , request ip {}" , phoneNum,getT().getInetAddress().getHostAddress());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return null;
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
