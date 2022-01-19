package org.beable.common.ioc.context.support;

import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.factory.config.BeanPostProcessor;
import org.beable.common.ioc.context.ApplicationContext;
import org.beable.common.ioc.context.ApplicationContextAware;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/26
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicaitonContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
