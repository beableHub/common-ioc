package org.beable.springframework.beans.factory.config;

import org.beable.springframework.beans.BeansException;

/**
 * @author qing.wu
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition 加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
