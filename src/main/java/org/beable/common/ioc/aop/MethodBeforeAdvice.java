package org.beable.common.ioc.aop;

import java.lang.reflect.Method;

/**
 * @author qing.wu
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
