package com.zp.server.io.alternation.impl;

import com.zp.server.io.alternation.inter.ProctolResponse;
import com.zp.server.io.alternation.inter.ResponseActionInter;

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
