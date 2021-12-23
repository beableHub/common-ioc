package org.beable.common.ioc;

import org.beable.common.beans.PropertyValue;
import org.beable.common.beans.PropertyValues;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanReference;
import org.beable.common.beans.factory.support.BeanDefinitionReader;
import org.beable.common.beans.factory.support.DefaultListableBeanFactory;
import org.beable.common.beans.factory.xml.XmlBeanDefinitionReader;
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


//    @Test
//    public void test_BeanFactory(){
//        // 初始化beanFactory
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // 注册bean
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
//        beanFactory.registerBeanDefinition("userService",beanDefinition);
//
//        // 第一次获取bean
//        UserService userService = (UserService) beanFactory.getBean("userService",20);
//        userService.queryUserInfo();
//
//        // 第二次获取bean
//        UserService singletonUserService = (UserService) beanFactory.getBean("userService","wuqing");
//        singletonUserService.queryUserInfo();
//    }


    @Test
    public void test_BeanFactory(){
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册userDao
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));


        // 注册bean
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService");
        singletonUserService.queryUserInfo("10002");
    }


    @Test
    public void test_xml(){
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:application-context.xml");

        // 获取bean
        // 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService");
        singletonUserService.queryUserInfo("10002");

    }
}
