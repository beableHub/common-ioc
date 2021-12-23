package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeanException;
import org.beable.common.core.io.Resource;
import org.beable.common.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resources) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;
}
