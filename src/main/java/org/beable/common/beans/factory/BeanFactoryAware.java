package org.beable.common.beans.factory;

import org.beable.common.beans.BeansException;

/**
 * @author killua
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
