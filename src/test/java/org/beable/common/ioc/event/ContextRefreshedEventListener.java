package org.beable.common.ioc.event;

import org.beable.common.context.ApplicationEvent;
import org.beable.common.context.event.ApplicationListener;
import org.beable.common.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新------");
    }
}