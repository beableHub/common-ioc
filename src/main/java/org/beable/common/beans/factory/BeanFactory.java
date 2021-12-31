package org.beable.common.beans.factory;

import org.beable.common.beans.BeansException;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name,Object... args) throws BeansException;

    <T> T getBean(String name,Class<T> requiredType) throws BeansException;
}
