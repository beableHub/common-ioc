package org.beable.common.aop;

/**
 * @author qing.wu
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
