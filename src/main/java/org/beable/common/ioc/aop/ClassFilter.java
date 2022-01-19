package org.beable.common.ioc.aop;

/**
 * @author qing.wu
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
