package com.zp.server.inter;


import com.zp.server.impl.SessionClient;

public interface ServerProtcolPub {

    /**
     * 服务启动
     */
    void start();

    /**
     * 服务关闭
     */
    void stop();

    /**
     * 连接管理池
     * @param sessionId
     * @param handler
     */
    void setClientToPool(String sessionId, RequestHandler handler);




}
