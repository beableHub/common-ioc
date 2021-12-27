package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.factory.ConfigurableBeanFactory;
import org.beable.common.beans.factory.FactoryBean;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanPostProcessor;
import org.beable.common.utils.ClassUtils;

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

    @Override
    public Object getBean(String name) throws BeansException {
        Object sharedBeanInstance = getSingleton(name);
        if (sharedBeanInstance != null){
            return getObjectForBeanInstance(sharedBeanInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object beanInstance = createBean(name, beanDefinition, null);
        return getObjectForBeanInstance(beanInstance,name);
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
}
