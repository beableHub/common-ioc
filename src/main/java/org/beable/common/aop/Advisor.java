package org.beable.common.aop;

import org.aopalliance.aop.Advice;

/**
 * @author qing.wu
 */
public interface Advisor {

    Advice getAdvice();
}