package com.zp.server.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.zp.server.inter.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@AllArgsConstructor
public abstract class RequestAbstractHandler implements RequestHandler {

    private SessionClient client;

    public SessionClient getClient(){
        return  client;
    }


    protected synchronized byte[] sendOutMsg(byte[] b){
        try {
            OutputStream out = client.getOutputStream();
            out.write(b, 0, b.length);
            out.flush();
            byte[] rb = new byte[1024];
            InputStream in = client.getInputStream();
            int len = 0;
            ByteOutputStream bos = new ByteOutputStream();
            while((len = in.read(rb))>0){
                bos.write(rb,0,len);
            }
            return bos.getBytes();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
