package org.beable.springframework.beans.factory;

import org.beable.springframework.beans.factory.config.BeanPostProcessor;
import org.beable.springframework.beans.factory.config.SingletonBeanRegistry;
import org.beable.springframework.util.StringValueResolver;

/**
 * @author qing.wu
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";


    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();

    /**
     * 添加一个新的BeanPostProcessor，它将应用于工厂创建的bean
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}
