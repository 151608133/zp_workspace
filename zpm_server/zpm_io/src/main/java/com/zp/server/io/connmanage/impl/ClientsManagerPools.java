package com.zp.server.io.connmanage.impl;

import com.zp.server.io.connmanage.inter.ClientManager;
import com.zp.server.io.create.inter.RequestHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component(value = "ClientsManagerPools")
public class ClientsManagerPools implements ClientManager {


    private ConcurrentHashMap<String,RequestHandler> clientMap = new ConcurrentHashMap<>();


    /**
     * 提交链接
     * @param key
     * @return
     */
    @Override
    public void submit(String key, RequestHandler client) {
        clientMap.put(key,client);
    }


    /**
     * 获取连接
     * @param key
     * @return
     */
    @Override
    public RequestHandler getClient(String key){
        return clientMap.get(key);
    }
}
