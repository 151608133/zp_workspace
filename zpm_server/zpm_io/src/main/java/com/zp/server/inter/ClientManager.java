package com.zp.server.inter;


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
