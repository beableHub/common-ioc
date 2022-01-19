package org.beable.common.ioc.context.support;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.factory.config.ConfigurableListableBeanFactory;
import org.beable.common.ioc.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author qing.wu
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
        loadDefinitions(defaultListableBeanFactory);
        this.beanFactory = defaultListableBeanFactory;
    }

    protected abstract void loadDefinitions(DefaultListableBeanFactory beanFactory);


    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
