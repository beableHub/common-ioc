package org.beable.springframework.beans.factory;

import org.beable.springframework.beans.BeansException;

/**
 * @author qing.wu
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
