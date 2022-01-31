package org.beable.springframework.beans;

import org.beable.springframework.core.NestedRuntimeException;

/**
 * @author qing.wu
 */
public class BeansException extends NestedRuntimeException {

    public BeansException(String name, Throwable e) {
        super(name);
        this.addSuppressed(e);
    }

    public BeansException(String name) {
        super(name);
    }
}
