package org.beable.common.ioc.event;

import org.beable.common.ioc.context.event.ApplicationListener;
import org.beable.common.ioc.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭-----");
    }
}
