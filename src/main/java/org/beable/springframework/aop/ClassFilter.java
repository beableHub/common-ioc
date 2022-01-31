package org.beable.springframework.aop;

/**
 * @author qing.wu
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
