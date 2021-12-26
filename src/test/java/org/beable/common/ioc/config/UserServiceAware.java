package org.beable.common.ioc.config;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.BeanClassLoaderAware;
import org.beable.common.beans.factory.BeanFactory;
import org.beable.common.beans.factory.BeanFactoryAware;
import org.beable.common.beans.factory.BeanNameAware;
import org.beable.common.context.ApplicationContext;
import org.beable.common.context.ApplicationContextAware;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/26
 */
public class UserServiceAware implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware,BeanFactoryAware {

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader is " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory is" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is " + name);
    }

    @Override
    public void setApplicaitonContext(ApplicationContext applicaitonContext) throws BeansException {
        System.out.println("ApplicationContext is" + applicaitonContext);
    }
}
