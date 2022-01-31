package org.beable.springframework.beans.factory.support;

import org.beable.springframework.beans.BeansException;
import org.beable.springframework.beans.factory.BeanFactory;

/**
 * @author qing.wu
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;
}
