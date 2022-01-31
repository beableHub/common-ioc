package org.beable.springframework.beans.factory.support;

import org.beable.springframework.beans.BeansException;
import org.beable.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        // TODO
        return null;
    }
}
