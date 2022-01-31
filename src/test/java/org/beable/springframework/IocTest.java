package org.beable.springframework;

import org.beable.springframework.aop.AdvisedSupport;
import org.beable.springframework.aop.TargetSource;
import org.beable.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.beable.springframework.aop.framework.JdkDynamicAopProxy;
import org.beable.springframework.beans.PropertyValue;
import org.beable.springframework.beans.PropertyValues;
import org.beable.springframework.beans.factory.config.BeanDefinition;
import org.beable.springframework.beans.factory.config.BeanReference;
import org.beable.springframework.beans.factory.support.BeanDefinitionReader;
import org.beable.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.beable.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.beable.springframework.context.support.ClassPathXmlApplicationContext;
import org.beable.springframework.interceptor.UserDaoInterceptor;
import org.beable.springframework.event.CustomEvent;
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

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:application-context.xml");

        // 获取bean
        // 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryCompany("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService");
        singletonUserService.queryLocation("10002");
    }

    @Test
    public void test_xml_context(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        // 获取bean
        // 第一次获取bean
        UserService userService = (UserService) context.getBean("userService");
        userService.queryCompany("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) context.getBean("userService");
        singletonUserService.queryLocation("10002");

    }

    @Test
    public void test_init_destroy(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        context.registerShutdownHook();
        // 获取bean
        // 第一次获取bean
        UserService userService = (UserService) context.getBean("userService");
        userService.queryCompany("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) context.getBean("userService");
        singletonUserService.queryLocation("10002");

    }

    @Test
    public void test_aware(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        context.registerShutdownHook();
        // 获取bean
        // 第一次获取bean
        UserService userService = (UserService) context.getBean("userService");
        userService.queryCompany("10001");

        // 第二次获取bean
        UserService singletonUserService = (UserService) context.getBean("userService");
        singletonUserService.queryLocation("10002");

    }

    @Test
    public void test_prototype(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = (UserService) applicationContext.getBean("userService", UserService.class);
        UserService userService02 = (UserService) applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

    }

    @Test
    public void test_factory_bean(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        applicationContext.registerShutdownHook();
        // 2. 调用代理方法
        UserService userService = (UserService) applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo("10001");
    }


    @Test
    public void test_event(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_dynamic(){

        UserDao userDao = new UserDao();

        userDao.setLocation("Beijing");
        userDao.setCompany("Alibaba");

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userDao));
        advisedSupport.setMethodInterceptor(new UserDaoInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.beable.common.ioc.IUserDao.*(..))"));

        IUserDao proxy = (IUserDao) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("查询用户所在城市信息:"+proxy.queryLocation());
        System.out.println("查询用户公司信息:"+proxy.queryCompany());
    }

    @Test
    public void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        IUserDao proxy =  applicationContext.getBean("userDao",IUserDao.class);
        System.out.println("查询用户所在城市信息:"+proxy.queryLocation());
        System.out.println("查询用户公司信息:"+proxy.queryCompany());
    }

    @Test
    public void test_component(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        UserService userService = applicationContext.getBean("userService",UserService.class);
        System.out.println("host:"+userService.queryHost());
        userService.queryLocation("test");
        userService.queryCompany("test");
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryLocation("test");
        userService.queryCompany("test");
        System.out.println("host：" + userService.queryHost());
    }


    @Test
    public void test_proxy_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryLocation("test");
        userService.queryCompany("test");
        System.out.println("host：" + userService.queryHost());
    }

    @Test
    public void test_circular(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        userService.queryHost();
    }
}
