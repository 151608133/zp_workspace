package com.zp.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStart {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        applicationContext.start();
    }
}
