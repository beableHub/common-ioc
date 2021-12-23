package org.beable.common.beans.factory.support;

import org.beable.common.beans.BeanException;
import org.beable.common.beans.BeanUtils;
import org.beable.common.beans.PropertyValue;
import org.beable.common.beans.PropertyValues;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanReference;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            // 填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeanException("Instantiation of bean failed", e);
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
}
