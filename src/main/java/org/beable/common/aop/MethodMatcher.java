package org.beable.common.aop;

import java.lang.reflect.Method;

/**
 * @author qing.wu
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
