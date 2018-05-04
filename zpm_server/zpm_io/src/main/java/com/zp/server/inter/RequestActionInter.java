package com.zp.server.inter;

public interface RequestActionInter {

    //读地址请求报文
    ProctolRequest getReadAddressRequest(String tabType);

    //设置地址
    ProctolRequest setAddressRequest(String[] soureAddr, String destAddr,String tabType);


    //设置地址
    ProctolRequest getSetTimerRequest(String[] addr,String date,String tabType);


    //读数据
    ProctolRequest getReadDataRequest(String[] addr,String tabType);

    //备用接口
    ProctolRequest getBakUseRequest(String tabType,Object... obj);

}
