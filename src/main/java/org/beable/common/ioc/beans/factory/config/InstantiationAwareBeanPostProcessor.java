package org.beable.common.ioc.beans.factory.config;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.PropertyValues;

/**
 * @author qing.wu
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在目标bean对象被实例化之前调用
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException{
        return null;
    }

    default PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException{
        return pvs;
    }

    /**
     * 在目标bean对象实例化之后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException{
        return true;
    }

    default Object getEarlyBeanReference(Object bean, String beanName){
        return bean;
    }
}
