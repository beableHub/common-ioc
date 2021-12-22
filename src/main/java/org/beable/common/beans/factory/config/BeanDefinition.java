package org.beable.common.beans.factory.config;

/**
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
