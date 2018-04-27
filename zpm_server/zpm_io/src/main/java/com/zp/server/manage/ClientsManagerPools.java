package com.zp.server.manage;

import com.zp.server.impl.SessionClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class ClientsManagerPools {


    private ConcurrentHashMap<String,SessionClient> clientMap = new ConcurrentHashMap<>();


    /**
     * 提交至map连接
     * @param key
     * @param client
     */
    void submit(String key, SessionClient client){
        clientMap.put(key,client);
    }

}
