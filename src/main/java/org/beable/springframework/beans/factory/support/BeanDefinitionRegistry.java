package org.beable.springframework.beans.factory.support;

import org.beable.springframework.beans.factory.config.BeanDefinition;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
