package org.beable.common.beans.factory.support;

import org.beable.common.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        this.singletonObjects.put(beanName,singletonObject);
    }
}
