package com.zp.server.io.connmanage.inter;


import com.zp.server.io.create.inter.RequestHandler;

public interface ClientManager {

    /**
     * 提交至map连接
     * @param key
     * @param client
     */
    void submit(String key, RequestHandler client);


    /**
     * 获取链接
     * @param key
     * @return
     */
    RequestHandler getClient(String key);
}
