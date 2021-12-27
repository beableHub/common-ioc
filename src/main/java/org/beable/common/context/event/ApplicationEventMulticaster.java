package org.beable.common.context.event;

import org.beable.common.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);
    
    void multicastEvent(ApplicationEvent event);
}
