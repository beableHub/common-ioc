package org.beable.common.ioc.beans.factory;

import org.beable.common.ioc.beans.BeansException;

/**
 * @author killua
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
