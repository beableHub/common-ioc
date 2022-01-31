package org.beable.springframework.context;

/**
 * @author qing.wu
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
