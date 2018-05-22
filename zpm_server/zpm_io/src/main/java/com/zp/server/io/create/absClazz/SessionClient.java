package com.zp.server.io.create.absClazz;

import com.zp.server.io.create.inter.Channel;


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

    public SessionClient(T t){
        this.t = t;
    }


    protected abstract String getSessionId();

}
