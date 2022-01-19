package org.beable.common.ioc.context.event;


import org.beable.common.ioc.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public interface ApplicationListener<E extends ApplicationEvent> {

    void onApplicationEvent(E event);
}
