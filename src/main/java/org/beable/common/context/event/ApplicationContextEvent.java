package org.beable.common.context.event;

import org.beable.common.context.ApplicationContext;
import org.beable.common.context.ApplicationEvent;

/**
 * @author qing.wu
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) super.getSource();
    }
}
