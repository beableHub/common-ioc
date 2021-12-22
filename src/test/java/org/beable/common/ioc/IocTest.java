package org.beable.common.ioc;

import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.BeanFactory;
import org.beable.common.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class IocTest {

//    @Test
//    public void test_BeanFactory(){
//        // 初始化beanFactory
//        BeanFactory beanFactory = new BeanFactory();
//
//        // 注册bean
//        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//
//        // 获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//    }


    @Test
    public void test_BeanFactory(){
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService",20);
        userService.queryUserInfo();

        // 第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService","wuqing");
        singletonUserService.queryUserInfo();
    }
}
