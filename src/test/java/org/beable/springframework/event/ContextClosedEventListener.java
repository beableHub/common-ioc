package org.beable.springframework.event;

import org.beable.springframework.context.event.ApplicationListener;
import org.beable.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭-----");
    }
}
