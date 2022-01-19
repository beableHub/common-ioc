package org.beable.common.ioc.beans.factory.support;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.factory.BeanFactory;

/**
 * @author qing.wu
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
