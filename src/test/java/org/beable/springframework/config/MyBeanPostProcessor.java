package org.beable.springframework.config;

import org.beable.springframework.UserDao;
import org.beable.springframework.beans.BeansException;
import org.beable.springframework.beans.factory.config.BeanPostProcessor;

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
