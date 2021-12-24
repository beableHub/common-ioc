package org.beable.common.beans.factory;

import org.beable.common.beans.BeansException;

import java.util.Map;

/**
 * @author qing.wu
 */
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    boolean containsBeanDefinition(String beanName);
}
