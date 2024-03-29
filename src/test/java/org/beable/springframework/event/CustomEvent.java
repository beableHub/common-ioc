package org.beable.springframework.event;

import org.beable.springframework.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source,Long id,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
