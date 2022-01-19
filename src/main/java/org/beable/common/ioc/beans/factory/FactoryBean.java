package org.beable.common.ioc.beans.factory;

/**
 * @author qing.wu
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
