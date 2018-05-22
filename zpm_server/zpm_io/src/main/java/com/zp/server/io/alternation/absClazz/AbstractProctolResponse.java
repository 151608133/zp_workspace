package com.zp.server.io.alternation.absClazz;

import com.zp.server.io.alternation.inter.ProctolResponse;
import com.zp.server.utils.ByteUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Arrays;

@Data
@Slf4j
public abstract class AbstractProctolResponse implements ProctolResponse {

    private byte[] b;

    private String address;//地址

    private String tableType;//表类型

    private String startHeader;// 起始桢

    private String controlCode;// 控制码

    private int length;//报文体长度

    private byte[] bodyBytes;//报文体字节

    private String bodyStr;//报文体16进制字符串

    private String endCode;//结束码

    private String vsCode;//校验码

    private boolean vsFlag;//校验标志  true -- 校验通过  false  -- 校验失败

    @Override
    public String getResponse() {
        return null;
    }

    public void setResponseByte(byte[] b){
        this.b = b;
    }


    public AbstractProctolResponse(byte[] b){
        this.b = b;
        initData();
    }

    public void initData() {
        byte[] temp = Arrays.copyOfRange(b, 0, b.length);
        this.startHeader = ByteUtils.toHexString1(Arrays.copyOfRange(temp, 0, 4));
        this.tableType = ByteUtils.toHexString1(Arrays.copyOfRange(temp, 4, 5));
        this.address = ByteUtils.toHexString1(Arrays.copyOfRange(temp, 5, 12));
        this.controlCode = ByteUtils.toHexString1(Arrays.copyOfRange(temp, 12, 13));
        this.length = new BigInteger(Arrays.copyOfRange(temp, 13, 14)).intValue();
        this.bodyBytes =Arrays.copyOfRange(temp, 14, 14+length);
        this.bodyStr = ByteUtils.toHexString1(bodyBytes);
        this.vsCode =  ByteUtils.toHexString1(Arrays.copyOfRange(temp,14+length,15+length));
        this.endCode = ByteUtils.toHexString1(Arrays.copyOfRange(temp,15+length,16+length));

        byte[] vsBytes = Arrays.copyOfRange(b, 3, b.length - 2);

        int out = getVerifyVsCode(vsBytes);
        int vsLen = new BigInteger(ByteUtils.hexStringToByte(vsCode)).intValue();

        if(out==vsLen){
            this.vsFlag= true;
        }else{
            this.vsFlag = false;
        }
        initBodyData(bodyBytes);
    }

    protected abstract void initBodyData(byte[] bodyBytes);


    protected int getVerifyVsCode(byte[] vsBytes){
        int out = 0;
        for (int i = 0;i<vsBytes.length;i++){
            out +=new BigInteger(1,new byte[]{vsBytes[i]}).intValue();
        }
        out =  out-256*(out/256);
        return out;
    }


    }
