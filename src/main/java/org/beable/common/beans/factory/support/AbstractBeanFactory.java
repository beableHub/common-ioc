package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeanException;
import org.beable.common.beans.factory.BeanFactory;
import org.beable.common.beans.factory.config.BeanDefinition;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeanException {
        Object bean = getSingleton(name);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,null);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeanException;
}
