package org.beable.common.beans;

/**
 * @author qing.wu
 */
public class BeanException extends RuntimeException{

    public BeanException(String name, Throwable e) {
        super(name);
        this.addSuppressed(e);
    }

    public BeanException(String name) {
        super(name);
    }
}
