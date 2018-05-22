package com.zp.server.io.create.impl;

import com.zp.server.io.alternation.impl.ReadeDataResponseProctol;
import com.zp.server.io.create.absClazz.RequestAbstractHandler;
import com.zp.server.io.alternation.inter.ProctolRequest;
import com.zp.server.io.alternation.inter.ProctolResponse;
import com.zp.server.io.create.absClazz.SessionClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketHandler extends RequestAbstractHandler {

    SocketHandler(SessionClient client){
        super(client);
    }

    @Override
    public ProctolResponse handleRequest(ProctolRequest request) {
        byte[]  b = sendOutMsg(request.getRequestByte());
        request.getTabEnum();
        ReadeDataResponseProctol responseProctol = new ReadeDataResponseProctol(b);
        return responseProctol;
    }



}
