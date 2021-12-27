package org.beable.common.context;

/**
 * @author qing.wu
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
