package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final Map<Class<?>,String[]> allBeanNamesByType = new ConcurrentHashMap<>(64);

    private final List<String> beanDefinitionNames = new ArrayList<>();


    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null){
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName);
        beanDefinitionMap.put(beanName,beanDefinition);
        if (existingDefinition == null){
            beanDefinitionNames.add(beanName);
        }
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null){
           return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String,T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames){
            Object beanInstance = getBean(beanName);
            if (beanInstance != null){
                result.put(beanName, (T) beanInstance);
            }
        }
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    private String[] getBeanNamesForType(Class<?> type){
        Map<Class<?>,String[]> cache = this.allBeanNamesByType;
        String[] resolvedBeanNames = cache.get(type);
        if (resolvedBeanNames != null){
            return resolvedBeanNames;
        }
        resolvedBeanNames = doGetBeanNamesForType(type);
        cache.put(type,resolvedBeanNames);
        return resolvedBeanNames;
    }

    private String[] doGetBeanNamesForType(Class<?> type){
        List<String> result = new ArrayList<>();

        for (String beanName: this.beanDefinitionNames){
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            if (type.isAssignableFrom(beanDefinition.getBeanClass())){
                result.add(beanName);
            }
        }
        return result.toArray(new String[0]);
    }


    @Override
    public void preInstantiateSingletons() throws BeansException {
        this.beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String>  beanNames = new ArrayList<>();
        for (Map.Entry<String,BeanDefinition> entry : beanDefinitionMap.entrySet()){
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)){
                beanNames.add(entry.getKey());
            }
        }
        if (beanNames.size() == 1){
            return getBean(beanNames.get(0),requiredType);
        }
        throw new BeansException(requiredType + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }
}
