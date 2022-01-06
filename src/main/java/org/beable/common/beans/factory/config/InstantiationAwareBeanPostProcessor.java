package org.beable.common.beans.factory.config;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.PropertyValues;

/**
 * @author qing.wu
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException{
        return null;
    }

    default PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException{
        return pvs;
    }
}
