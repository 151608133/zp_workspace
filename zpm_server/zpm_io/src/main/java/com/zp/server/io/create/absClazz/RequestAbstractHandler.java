package com.zp.server.io.create.absClazz;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.zp.server.io.create.inter.RequestHandler;
import com.zp.server.io.create.absClazz.SessionClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

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
            InputStream in = client.getInputStream();
            byte[] rb = new byte[14];//先读前14个字节  包含  FEFEFE 68 为帧起始符 68H 20 为仪表类型 T 地址码  控制位
            ByteOutputStream bos = new ByteOutputStream();
            in.read(rb,0,rb.length);
            bos.write(rb,0,rb.length);
            // 读取报文体长度
            rb = new byte[1];
            in.read(rb,0,1);//读取 一个字节
            bos.write(rb,0,1);
            int bodyLength = new BigInteger(1,rb).intValue();
            // 读取报文体
            rb = new byte[bodyLength];
            in.read(rb,0,bodyLength);
            bos.write(rb,0,bodyLength);

            // 读取校验码 和  结束符
            rb = new byte[2];
            in.read(rb,0,2);
            bos.write(rb,0,2);

            return bos.getBytes();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
