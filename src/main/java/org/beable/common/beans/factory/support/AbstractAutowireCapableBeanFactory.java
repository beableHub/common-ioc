package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeansException;
import org.beable.common.beans.BeanUtils;
import org.beable.common.beans.PropertyValue;
import org.beable.common.beans.PropertyValues;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanPostProcessor;
import org.beable.common.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
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
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            // 填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //
            bean = initializeBean(bean,beanName,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
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
        // 执行BeanPostProcessor的before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 执行初始化方法
        invokeInitMethods(beanName,wrappedBean,beanDefinition);

        // 执行BeanPostProcessor的after处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);

        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

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
