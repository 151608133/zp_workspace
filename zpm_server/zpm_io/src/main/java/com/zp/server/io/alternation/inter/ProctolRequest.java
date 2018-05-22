package com.zp.server.io.alternation.inter;


import com.zp.server.common.TabEnum;

/**
 * 请求报文对象
 */
public interface ProctolRequest {


    public byte[] getRequestByte();


    public TabEnum getTabEnum();


}
