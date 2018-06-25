package com.zp.server.io.create.inter;

import com.zp.server.io.alternation.inter.ProctolRequest;
import com.zp.server.io.alternation.inter.ProctolResponse;
import com.zp.server.io.create.absClazz.SessionClient;

/**
 * 协议处理
 */
public interface RequestHandler {


    ProctolResponse handleRequest(ProctolRequest request);


    public SessionClient getClient();


    }
