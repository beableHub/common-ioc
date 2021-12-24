package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.BeanFactory;

/**
 * @author qing.wu
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
