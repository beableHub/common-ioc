package org.beable.common.ioc.config;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.PropertyValue;
import org.beable.common.ioc.beans.PropertyValues;
import org.beable.common.ioc.beans.factory.config.BeanDefinition;
import org.beable.common.ioc.beans.factory.config.BeanFactoryPostProcessor;
import org.beable.common.ioc.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userDao");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","YYDS"));
    }
}
