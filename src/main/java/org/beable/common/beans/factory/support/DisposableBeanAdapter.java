package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.DisposableBean;
import org.beable.common.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author qing.wu
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
        if (destroyMethod == null){
            throw new BeansException("Could not find a destroy method named '" + destroyMethodName + "' on bean with name '"+ beanName +"'");
        }
        destroyMethod.invoke(bean);
    }
}
