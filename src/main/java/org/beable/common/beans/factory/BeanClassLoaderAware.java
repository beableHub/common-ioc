package org.beable.common.beans.factory;

/**
 * @author killua
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
