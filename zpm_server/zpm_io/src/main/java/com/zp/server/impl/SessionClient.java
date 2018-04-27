package com.zp.server.impl;

import com.zp.server.inter.Channel;


/**
 * @param <T>
 */
public abstract  class SessionClient<T> implements Channel {


    private T t;

    public T getT(){
        return t;
    }

    public void setT(T t){
        this.t=t;
    }

    SessionClient(T t){
        this.t = t;
    }

}
