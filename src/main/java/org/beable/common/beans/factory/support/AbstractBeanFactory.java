package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.ConfigurableBeanFactory;
import org.beable.common.beans.factory.FactoryBean;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanPostProcessor;
import org.beable.common.util.ClassUtils;
import org.beable.common.util.StringValueResolver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /** String resolvers to apply e.g. to annotation attribute values. */
    private final List<StringValueResolver> embeddedValueResolvers = new CopyOnWriteArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object sharedBeanInstance = getSingleton(name);
        if (sharedBeanInstance != null){
            return (T) getObjectForBeanInstance(sharedBeanInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object beanInstance = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(beanInstance,name);
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null){
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            object = getObjectFromFactoryBean(factoryBean,beanName);
        }
        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = (beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        if (value == null){
            return null;
        }
        String result = value;
        for (StringValueResolver resolver: this.embeddedValueResolvers){
            result = resolver.resolveStringValue(value);
            if (result == null){
                return null;
            }
        }
        return result;
    }
}
