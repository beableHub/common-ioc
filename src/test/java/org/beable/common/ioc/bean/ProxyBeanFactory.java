package org.beable.common.ioc.bean;

import org.beable.common.ioc.beans.factory.FactoryBean;
import org.beable.common.ioc.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String,String> proxyMap = new HashMap<>();
            proxyMap.put("10001", "proxyMapA");
            proxyMap.put("10002", "proxyMapB");
            proxyMap.put("10003", "proxyMapC");
            return "你被代理了 " + method.getName() + "：" + proxyMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{IUserDao.class},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
