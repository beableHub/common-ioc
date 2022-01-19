package org.beable.common.ioc.aop;

/**
 * @author qing.wu
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
