package org.beable.common.ioc.beans.factory.support;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.core.io.Resource;
import org.beable.common.ioc.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
