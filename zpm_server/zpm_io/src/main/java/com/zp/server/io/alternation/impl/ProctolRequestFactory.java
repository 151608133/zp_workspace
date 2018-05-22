package com.zp.server.io.alternation.impl;

import com.zp.server.common.TabEnum;
import com.zp.server.io.alternation.inter.ProctolRequest;
import com.zp.server.io.alternation.inter.RequestActionInter;
import com.zp.server.utils.ByteUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * 请求报文组装工厂
 */
@Component
public class ProctolRequestFactory implements RequestActionInter {



    /**
     * 读地址请求报文
     * @return
     */
    @Override
    public ProctolRequest getReadAddressRequest(TabEnum tabEnum) {
        return new ProctolRequest() {
            @Override
            public byte[] getRequestByte() {
                String req="FEFEFE68"+tabEnum.content+"AAAAAAAAAAAAAA0303810A01C016";
                return ByteUtils.hexStringToByte(req);
            }

            @Override
            public TabEnum getTabEnum() {
                return tabEnum;
            }
        };
    }


    /**
     * 设置地址
     * @param soureAddr
     * @param destAddr
     * @return
     */
    @Override
    public ProctolRequest setAddressRequest(String[] soureAddr, String destAddr, TabEnum tabEnum) {
        return  new ProctolRequest() {
            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }

            @Override
            public TabEnum getTabEnum() {
                return tabEnum;
            }
        };
    }

    @Override
    public ProctolRequest getSetTimerRequest(String[] addr, String date, TabEnum tabEnum) {
            return new ProctolRequest() {
                @Override
                public byte[] getRequestByte() {
                    return new byte[0];
                }

                @Override
                public TabEnum getTabEnum() {
                    return null;
                }
            };
    }

    @Override
    public ProctolRequest getReadDataRequest(String[] addr, TabEnum tabEnum) {
        return  new ProctolRequest() {
            @Override
            public byte[] getRequestByte() {
                String header="FEFEFE";
                String start = "68"+tabEnum.content;
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
                int out = 0;
                for (int i = 0;i<bs.length;i++){
                    out +=new BigInteger(1,new byte[]{bs[i]}).intValue();
                }
                out =  out-256*(out/256);
                String hex = Integer.toHexString(out);
                String req = header+body+hex+"16";
                return ByteUtils.hexStringToByte(req);
            }

            @Override
            public TabEnum getTabEnum() {
                return null;
            }
        };
    }

    @Override
    public ProctolRequest getBakUseRequest(TabEnum tabEnum, Object... obj) {
        return new ProctolRequest() {
            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }

            @Override
            public TabEnum getTabEnum() {
                return null;
            }
        };
    }
}
