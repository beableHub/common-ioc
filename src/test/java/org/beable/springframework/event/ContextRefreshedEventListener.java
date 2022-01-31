package org.beable.springframework.event;

import org.beable.springframework.context.event.ApplicationListener;
import org.beable.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新------");
    }
}
