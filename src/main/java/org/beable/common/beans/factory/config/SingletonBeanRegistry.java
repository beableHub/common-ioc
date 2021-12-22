package org.beable.common.beans.factory.config;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
