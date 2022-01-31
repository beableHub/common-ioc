package org.beable.springframework.beans.factory;

import org.beable.springframework.beans.BeansException;

/**
 * @author killua
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
