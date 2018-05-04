package com.zp.server.impl;

import com.zp.server.inter.ProctolRequest;
import com.zp.server.inter.ProctolResponse;
import com.zp.server.inter.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;

@Slf4j
public class SocketHandler extends RequestAbstractHandler{

    SocketHandler(SessionClient client){
        super(client);
    }

    @Override
    public synchronized ProctolResponse handleRequest(ProctolRequest request) {
        byte[]  b = sendOutMsg(request.getRequestByte());


        return null;
    }



}
