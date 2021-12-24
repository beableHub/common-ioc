package org.beable.common.beans;

import java.lang.reflect.Field;

/**
 * @author qing.wu
 */
public class BeanUtils {

    private BeanUtils(){}

    public static void setFieldValue(Object bean, String name, Object value){
        Class<?> clazz = bean.getClass();
        try {
            Field f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            f.set(bean,value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("",e);
        }
    }
}
