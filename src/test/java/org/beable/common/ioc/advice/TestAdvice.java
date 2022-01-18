package org.beable.common.ioc.advice;

import org.beable.common.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class TestAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}