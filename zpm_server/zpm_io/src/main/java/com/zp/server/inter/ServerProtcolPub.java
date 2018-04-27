package com.zp.server.inter;

import java.util.concurrent.ThreadPoolExecutor;

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
     * @param pools
     */
    void setPools(ThreadPoolExecutor pools);




}
