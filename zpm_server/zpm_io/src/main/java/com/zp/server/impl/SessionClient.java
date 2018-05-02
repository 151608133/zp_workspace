package com.zp.server.impl;

import com.zp.server.inter.Channel;


/**
 * @param <T>
 */
public abstract  class SessionClient<T> implements Channel {


    private T t;

    private String sessionId;

    public T getT(){
        return t;
    }

    public void setT(T t){
        this.t=t;
    }

    public void setSessionId(){
        this.sessionId = getSessionId();
    }

    SessionClient(T t){
        this.t = t;
    }


    protected abstract String getSessionId();

}
