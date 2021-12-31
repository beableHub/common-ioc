package org.beable.common.aop;

/**
 * @author qing.wu
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
