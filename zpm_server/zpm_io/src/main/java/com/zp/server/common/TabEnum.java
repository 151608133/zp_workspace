package com.zp.server.common;

public enum TabEnum {

    COLD_TAB("COLD_TAB","10"),//冷水表
    LIVE_HOT_TAB("LIVE_HOT_TAB","11"),//生活热水表
    DRINK_TAB("DRINK_TAB","12"), //直饮水水表
    MED_WAT_TAB("MED_WAT_TAB","13"),//中水水表
    HOT_TAB_HOT("HOT_TAB_HOT","20"), //热量表（计热量）
    HOT_TAB_CODE("HOT_TAB_CODE","21"),//热量表（计冷量）
    GAS_TAB("GAS_TAB","30"), //燃气表
    ELEC_TAB("ELEC_TAB","40");//如：电度表


    public String tabName;
    public String content;

    TabEnum(String tabName,String content){
        this.tabName = tabName;
        this.content = content;
    }

}
