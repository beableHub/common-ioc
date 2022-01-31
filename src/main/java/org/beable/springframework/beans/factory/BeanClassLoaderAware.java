package org.beable.springframework.beans.factory;

/**
 * @author killua
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
