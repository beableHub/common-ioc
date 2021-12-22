package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeanException;
import org.beable.common.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null){
            throw new BeanException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        Object bean = getSingleton(name);
        if (bean != null){
           return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,args);
    }
}
