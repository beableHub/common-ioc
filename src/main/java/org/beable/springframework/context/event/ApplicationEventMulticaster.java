package org.beable.springframework.context.event;

import org.beable.springframework.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);
    
    void multicastEvent(ApplicationEvent event);
}
