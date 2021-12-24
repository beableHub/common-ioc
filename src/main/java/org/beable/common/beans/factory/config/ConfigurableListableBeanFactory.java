package org.beable.common.beans.factory.config;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.ConfigurableBeanFactory;
import org.beable.common.beans.factory.ListableBeanFactory;

/**
 * @author qing.wu
 */
public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory,AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 确保所有非延迟初始化单例都被实例化
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
