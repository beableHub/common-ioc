package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.BeanUtils;
import org.beable.common.beans.PropertyValue;
import org.beable.common.beans.PropertyValues;
import org.beable.common.beans.factory.*;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanPostProcessor;
import org.beable.common.beans.factory.config.BeanReference;
import org.beable.common.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.beable.common.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    private final InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        // 判断是否返回代理Bean对象
        Object bean = resolveBeforeInstantiation(beanName,beanDefinition);
        if (null != bean){
            return bean;
        }
        return doCreateBean(beanName,beanDefinition,args);
    }


    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition,Object[] args){
        Object bean = null;
        try {
            // 实例化
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 处理循环依赖，将实例化后的Bean对象提前放入缓存中暴露出来
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, finalBean));
            }
            // 实例化后判断
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation) {
                return bean;
            }
            // 在设置bean属性之前，允许BeanPostProcessor修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(bean, beanName, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        Object exposedObject = bean;
        if (beanDefinition.isSingleton()) {
            exposedObject = getSingleton(beanName);
            registerSingleton(beanName,exposedObject);
        }
        return exposedObject;
    }

    private Object getEarlyBeanReference(String beanName, Object bean) {
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                exposedObject = ((InstantiationAwareBeanPostProcessor)beanPostProcessor).getEarlyBeanReference(bean,beanName);
                if (exposedObject != null){
                    return exposedObject;
                }
            }
        }
        return exposedObject;
    }


    /**
     * Bean 实例化后对于返回 false 的对象，不在执行后续设置 Bean 对象属性的操作
     *
     * @param beanName
     * @param bean
     * @return
     */
    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean,beanName)){
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor)
                        .postProcessPropertyValues(beanDefinition.getPropertyValues(),bean,beanName);
                if (null != pvs){
                    for (PropertyValue propertyValue : pvs.getPropertyValues()){
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(),beanName);
        if (null != bean){
            bean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor: getBeanPostProcessors()){
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result = ((InstantiationAwareBeanPostProcessor)beanPostProcessor).postProcessBeforeInstantiation(beanClass,beanName);
                if (null != result) {
                    return result;
                }
            }
        }
        return null;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StringUtils.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue: propertyValues.getPropertyValues()){
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtils.setFieldValue(bean,name,value);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructor = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor: declaredConstructors){
            // 多个构造器的情况下如何处理？ TODO -- https://segmentfault.com/a/1190000022521744
            if (null != args && isConstructorArgs(ctor.getParameterTypes(),args)){
                constructor = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    protected Boolean isConstructorArgs(Class[] classes,Object[] args){
        if (classes.length != args.length){
            return false;
        }
        for (int i = 0; i < classes.length; i++){
            if (!Objects.equals(args[i].getClass().getName(),classes[i].getName())){
                return false;
            }
        }
        return true;
    }


    protected InstantiationStrategy getInstantiationStrategy(){
        return this.instantiationStrategy;
    }


    private Object initializeBean(Object bean, String beanName,BeanDefinition beanDefinition) throws BeansException {

        // invokeAwareMethods
        if (bean instanceof Aware){
            if (bean instanceof BeanFactoryAware){
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware)bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware){
                ((BeanNameAware)bean).setBeanName(beanName);
            }
        }

        // 执行BeanPostProcessor的before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        // 执行初始化方法
        try {
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Inovocation  of init method of bean[" + beanName + "] failed", e);
        }

        // 执行BeanPostProcessor的after处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }


    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 实现接口InitializingBean
        if (bean instanceof InitializingBean){
            ((InitializingBean)bean).afterPropertiesSet();
        }

        // 配置init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (StringUtils.isEmpty(initMethodName)){
            return;
        }
        Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
        if (initMethod == null){
            throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name " + beanName + "'");
        }
        initMethod.invoke(bean);
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException{
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if (current != null){
                result = current;
            }
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if (current != null){
                result = current;
            }
        }
        return result;
    }

}
