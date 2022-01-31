package org.beable.springframework.config;

import org.beable.springframework.beans.BeansException;
import org.beable.springframework.beans.PropertyValue;
import org.beable.springframework.beans.PropertyValues;
import org.beable.springframework.beans.factory.config.BeanDefinition;
import org.beable.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.beable.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userDao");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","YYDS"));
    }
}
