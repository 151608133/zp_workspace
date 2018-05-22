package com.zp.server.io.create.inter;

import com.zp.server.io.alternation.inter.ProctolRequest;
import com.zp.server.io.alternation.inter.ProctolResponse;

/**
 * 协议处理
 */
public interface RequestHandler {


    ProctolResponse handleRequest(ProctolRequest request);




}
