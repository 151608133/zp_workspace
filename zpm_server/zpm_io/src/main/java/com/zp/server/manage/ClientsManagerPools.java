package com.zp.server.manage;

import com.zp.server.inter.ClientManager;
import com.zp.server.inter.RequestHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class ClientsManagerPools implements ClientManager {


    private ConcurrentHashMap<String,RequestHandler> clientMap = new ConcurrentHashMap<>();


    @Override
    public void submit(String key, RequestHandler client) {
        clientMap.put(key,client);
    }

    /**
     * 提交链接
     * @param key
     * @return
     */
    @Override
    public RequestHandler getClient(String key){
        return clientMap.get(key);
    }
}
