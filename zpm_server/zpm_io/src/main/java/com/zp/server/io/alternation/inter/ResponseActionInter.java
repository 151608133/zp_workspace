package com.zp.server.io.alternation.inter;


import com.zp.server.io.alternation.inter.ProctolResponse;

public interface ResponseActionInter {


    ProctolResponse getReadAddressResponse(Byte[] bytes);

    //设置地址
    ProctolResponse getSetAddressResponse(Byte[] bytes);

    //设置地址
    ProctolResponse getSetTimerResponse(Byte[] bytes);

    //读数据
    ProctolResponse getReadDataResponse(Byte[] bytes);

    //备用接口
    ProctolResponse getBakUseResponse(Byte[] bytes);

}
