package com.zp.server.factory;

import com.zp.server.inter.ProctolRequest;
import com.zp.server.inter.RequestActionInter;

/**
 * 协议报文
 */
public class ProctolRequestFactory implements RequestActionInter {


    @Override
    public ProctolRequest getReadAddressRequest() {
        return new ProctolRequest() {
            @Override
            public String getRequest() {
                return null;
            }

            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }
        };
    }

    @Override
    public ProctolRequest setAddressRequest(String[] soureAddr, String destAddr) {
        return new ProctolRequest() {
            @Override
            public String getRequest() {
                return null;
            }

            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }
        };
    }

    @Override
    public ProctolRequest getSetTimerRequest(String[] addr, String date) {
        return new ProctolRequest() {
            @Override
            public String getRequest() {
                return null;
            }

            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }
        };
    }

    @Override
    public ProctolRequest getReadDataRequest(String[] addr) {
        return new ProctolRequest() {
            @Override
            public String getRequest() {
                return null;
            }

            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }
        };
    }

    @Override
    public ProctolRequest getBakUseRequest(Object... obj) {
        return new ProctolRequest() {
            @Override
            public String getRequest() {
                return null;
            }

            @Override
            public byte[] getRequestByte() {
                return new byte[0];
            }
        };
    }
}
