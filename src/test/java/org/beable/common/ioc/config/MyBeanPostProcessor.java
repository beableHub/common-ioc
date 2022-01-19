package org.beable.common.ioc.config;

import org.beable.common.ioc.UserDao;
import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.factory.config.BeanPostProcessor;

import java.util.Objects;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals("userDao",beanName)){
            UserDao userDao = (UserDao) bean;
            userDao.setLocation("Beijing");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
