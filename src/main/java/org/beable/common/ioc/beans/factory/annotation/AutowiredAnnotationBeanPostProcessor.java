package org.beable.common.ioc.beans.factory.annotation;

import org.beable.common.ioc.beans.BeanUtils;
import org.beable.common.ioc.beans.BeansException;
import org.beable.common.ioc.beans.PropertyValues;
import org.beable.common.ioc.beans.factory.BeanFactory;
import org.beable.common.ioc.beans.factory.BeanFactoryAware;
import org.beable.common.ioc.beans.factory.config.ConfigurableListableBeanFactory;
import org.beable.common.ioc.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.beable.common.ioc.util.ClassUtils;

import java.lang.reflect.Field;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2022/1/6
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields){
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation){
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtils.setFieldValue(bean, field.getName(), value);
            }
        }

        for (Field field : declaredFields){
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation){
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName,fieldType);
                }else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtils.setFieldValue(bean,field.getName(),dependentBean);
            }
        }
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }
}
