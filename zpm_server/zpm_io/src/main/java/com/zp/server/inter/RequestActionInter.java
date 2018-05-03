package com.zp.server.inter;

public interface RequestActionInter {

    //读地址请求报文
    ProctolRequest getReadAddressRequest();

    //设置地址
    ProctolRequest setAddressRequest(String[] soureAddr, String destAddr);


    //设置地址
    ProctolRequest getSetTimerRequest(String[] addr,String date);


    //读数据
    ProctolRequest getReadDataRequest(String[] addr);

    //备用接口
    ProctolRequest getBakUseRequest(Object... obj);

}
