package org.beable.common.beans.factory;

/**
 * @author qing.wu
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
