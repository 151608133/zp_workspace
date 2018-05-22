package com.zp.server.io.alternation.inter;

import com.zp.server.common.TabEnum;
import com.zp.server.io.alternation.inter.ProctolRequest;

public interface RequestActionInter {

    //读地址请求报文
    ProctolRequest getReadAddressRequest(TabEnum tabEnum);

    //设置地址
    ProctolRequest setAddressRequest(String[] soureAddr, String destAddr,TabEnum tabEnum);


    //设置地址
    ProctolRequest getSetTimerRequest(String[] addr,String date,TabEnum tabEnum);


    //读数据
    ProctolRequest getReadDataRequest(String[] addr,TabEnum tabEnum);

    //备用接口
    ProctolRequest getBakUseRequest(TabEnum tabEnum,Object... obj);

}
