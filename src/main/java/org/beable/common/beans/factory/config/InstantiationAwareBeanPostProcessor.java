package org.beable.common.beans.factory.config;

import org.beable.common.beans.BeansException;

/**
 * @author qing.wu
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException;
}
