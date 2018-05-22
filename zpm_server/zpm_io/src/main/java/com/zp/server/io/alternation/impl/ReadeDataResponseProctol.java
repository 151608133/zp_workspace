package com.zp.server.io.alternation.impl;

import com.zp.server.common.TabEnum;
import com.zp.server.io.alternation.absClazz.AbstractProctolResponse;
import com.zp.server.utils.ByteUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Data
@Slf4j
public class ReadeDataResponseProctol extends AbstractProctolResponse {


    private String data1Flag;//数据标志

    private String data2Flag;//数据标志

    private String serial;//序号

    private String balanceUnit;//结算单位

    private String balanceData;//结算数据

    private String dayDataUnit;//当前热量单位

    private String dayData;//当前热量

    private String powerRateUnit;//功率单位

    private String powerRate;//功率

    private String flowSpeedUnit;//流速单位

    private String flowSpeed;//流速

    private String flowUnit;// 流量单位

    private String flowData;//流量数据

    private String inTemp;//进口温度

    private String outTemp;//出口温度

    private String workTime;//工作时间

    private String sysTime;//系统时间

    private String sysStatus;//系统状态字


    public ReadeDataResponseProctol(byte[] b) {
        super(b);
    }

    @Override
    protected void initBodyData(byte[] bodyBytes) {
        data1Flag = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,0,1));
        data2Flag = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,1,2));
        serial = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,2,3));

        balanceUnit = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,3,4));// 结算单位

        balanceData = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,4,8));// 结算数据 //TODO 需要逆序

        dayDataUnit =  ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,8,9));// 当前热量单位

        dayData =  ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,9,13));// 当前热量 //TODO 需要逆序

        powerRateUnit =  ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,13,14));// 热功率单位

        powerRate = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,14,18));// 热功率 //TODO 需要逆序

        flowSpeedUnit =ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,18,19));// 流速单位

        flowSpeed =ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,19,23));//流速 //TODO 需要逆序

        flowUnit =ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,23,24));//流量单位

        flowData =ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,24,28));//流量数据  //TODO 需要逆序

        inTemp = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,28,31));//进口温度 //TODO 需要逆序

        outTemp = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,31,34));//出口温度 //TODO 需要逆序

        workTime = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,34,37));//系统日期时间 //TODO 需要逆序

        sysTime = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,37,44));//系统日期时间 //TODO 需要逆序

        sysStatus = ByteUtils.bytesToHexString(Arrays.copyOfRange(bodyBytes,44,46));// 系统状态

    }

    @Override
    public TabEnum getTabEnum() {
        return null;
    }

    @Override
    public String toString(){
        return ByteUtils.bytesToHexString(getB());
    }
}
