package org.beable.common.beans.factory;

import org.beable.common.beans.factory.config.BeanPostProcessor;
import org.beable.common.beans.factory.config.SingletonBeanRegistry;

/**
 * @author qing.wu
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {


    /**
     * 添加一个新的BeanPostProcessor，它将应用于工厂创建的bean
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    void destroySingletons();
}
