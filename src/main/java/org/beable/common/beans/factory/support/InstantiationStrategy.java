package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeanException;
import org.beable.common.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args) throws BeanException;
}
