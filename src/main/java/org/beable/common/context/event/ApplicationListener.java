package org.beable.common.context.event;


import org.beable.common.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public interface ApplicationListener<E extends ApplicationEvent> {

    void onApplicationEvent(E event);
}
