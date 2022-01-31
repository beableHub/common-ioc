package org.beable.springframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.beable.springframework.aop.Pointcut;
import org.beable.springframework.aop.PointcutAdvisor;

/**
 * @author qing.wu
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return this.pointcut;
    }

    public String getExpression() {
        return expression;
    }

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }
}
