package org.beable.springframework.core;

/**
 * @author qing.wu
 */
public abstract class NestedRuntimeException extends RuntimeException{

    public NestedRuntimeException(String msg){super(msg);}

    public NestedRuntimeException(String msg,Throwable cause){super(msg,cause);}
}
