package com.zp.server.io.listener;

import com.zp.server.io.create.impl.SocketServerPub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ServerInitListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()!=null){
            SocketServerPub bean = event.getApplicationContext().getBean(SocketServerPub.class);
            bean.start();
        }
    }
}
