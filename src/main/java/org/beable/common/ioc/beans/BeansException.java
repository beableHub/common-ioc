package org.beable.common.ioc.beans;

import org.beable.common.ioc.core.NestedRuntimeException;

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
