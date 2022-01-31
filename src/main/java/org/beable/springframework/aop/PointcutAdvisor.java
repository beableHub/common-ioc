package org.beable.springframework.aop;

/**
 * @author qing.wu
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
