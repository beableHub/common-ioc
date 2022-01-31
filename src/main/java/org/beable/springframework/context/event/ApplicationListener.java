package org.beable.springframework.context.event;


import org.beable.springframework.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public interface ApplicationListener<E extends ApplicationEvent> {

    void onApplicationEvent(E event);
}
