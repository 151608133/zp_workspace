package com.zp.server.timer;

import com.zp.server.common.Customer;
import com.zp.server.io.connmanage.inter.ClientManager;
import com.zp.server.io.alternation.inter.ProctolResponse;
import com.zp.server.io.alternation.inter.RequestActionInter;
import com.zp.server.io.create.inter.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dataAcqTimer")
public class DataAcqusitionTimer {


    @Autowired
    private ClientManager manager;

    @Autowired
    private RequestActionInter requestActionFactory;


    public void execute(){
        //拿到数据库里所有的采集器信息  然后获取采集器Id
        RequestHandler client = manager.getClient("seriralId");
        ProctolResponse proctolResponse = client.handleRequest(requestActionFactory.getReadDataRequest(new String[]{}, Customer.HOT_TAB_CODE));

    }

}
