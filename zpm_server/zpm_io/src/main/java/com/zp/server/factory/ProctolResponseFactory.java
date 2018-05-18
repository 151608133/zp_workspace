package com.zp.server.factory;

import com.zp.server.inter.ProctolResponse;
import com.zp.server.inter.ResponseActionInter;

public class ProctolResponseFactory implements ResponseActionInter {

    @Override
    public ProctolResponse getReadAddressResponse(Byte[] bytes) {
        return null;
    }

    @Override
    public ProctolResponse getSetAddressResponse(Byte[] bytes) {
        return null;
    }

    @Override
    public ProctolResponse getSetTimerResponse(Byte[] bytes) {
        return null;
    }

    @Override
    public ProctolResponse getReadDataResponse(Byte[] bytes) {
        return null;
    }

    @Override
    public ProctolResponse getBakUseResponse(Byte[] bytes) {
        return null;
    }
}
