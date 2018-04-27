package com.zp.server.impl;

import com.zp.server.inter.ProctolRequest;
import com.zp.server.inter.ProctolResponse;
import com.zp.server.inter.RequestHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SocketHandler implements RequestHandler{

    private SessionClient client;

    @Override
    public ProctolResponse handleRequest(ProctolRequest request) {
        return null;
    }

}
