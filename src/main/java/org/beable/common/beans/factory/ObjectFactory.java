package org.beable.common.beans.factory;

import org.beable.common.beans.BeansException;

/**
 * @author qing.wu
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
