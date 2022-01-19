package org.beable.common.ioc.beans.factory;

/**
 * @author killua
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
