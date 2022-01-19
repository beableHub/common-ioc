package org.beable.common.ioc.beans.factory;

import org.beable.common.ioc.beans.BeansException;

/**
 * @author qing.wu
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
