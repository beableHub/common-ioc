package org.beable.common.ioc.context;

/**
 * @author qing.wu
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
