package com.zp.server.factory;

import com.zp.server.inter.ProctolRequest;
import com.zp.server.inter.RequestActionInter;
import com.zp.server.utils.ByteUtils;

import java.math.BigInteger;

/**
 * 协议报文
 */
public class ProctolRequestFactory implements RequestActionInter {


    /**
     * 读地址请求报文
     * @return
     */
    @Override
    public ProctolRequest getReadAddressRequest(String tabType) {
        return () -> {
            String req="FEFEFE68"+tabType+"AAAAAAAAAAAAAA0303810A01C016";
            return ByteUtils.hexStringToByte(req);
        };
    }


    /**
     * 设置地址
     * @param soureAddr
     * @param destAddr
     * @return
     */
    @Override
    public ProctolRequest setAddressRequest(String[] soureAddr, String destAddr, String tabType) {
        return () -> new byte[0];
    }

    @Override
    public ProctolRequest getSetTimerRequest(String[] addr, String date, String tabType) {
        return () -> new byte[0];
    }

    @Override
    public ProctolRequest getReadDataRequest(String[] addr, String tabType) {
        return () -> {
            String header="FEFEFE";
            String start = "68"+tabType;
            String address="";
            for (int i=addr.length;i>0;i--){
                address+=addr[i-1];
            }
            int len = 7-addr.length;
            for (int i = 0;i<len;i++){
                address+="00";
            }
            String body = start+address+"0103901F01";
            byte[] bs = ByteUtils.hexStringToByte(body);
            String offIn = new BigInteger(1, bs).toString(10);
            int out =  Integer.parseInt(offIn)-256*(Integer.parseInt(offIn)/256);
            String hex = Integer.toHexString(out);
            String req = header+body+hex+"16";
            return ByteUtils.hexStringToByte(req);
        };
    }

    @Override
    public ProctolRequest getBakUseRequest(String tabType, Object... obj) {
        return () -> new byte[0];
    }
}
