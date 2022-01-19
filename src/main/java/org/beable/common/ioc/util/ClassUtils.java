package org.beable.common.ioc.util;

import cn.hutool.core.util.ClassUtil;
import org.beable.common.ioc.stereotype.Component;

import java.util.Set;

/**
 * @author qing.wu
 */
public class ClassUtils {

    private ClassUtils(){}

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        // 获取当前线程上下文类加载器
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }catch(Throwable e){}
        if (cl != null){
            return cl;
        }
        // 获取当前类加载器
        try {
            cl = ClassUtils.class.getClassLoader();
        }catch (Throwable e){}
        if (cl != null){
            return cl;
        }
        // 获取系统启动类加载器
        try {
            cl = ClassLoader.getSystemClassLoader();
        }catch (Throwable e){}
        return cl;
    }

    public static Set<Class<?>> scanPackageByAnnotation(String basePackage, Class<Component> componentClass) {
        return ClassUtil.scanPackageByAnnotation(basePackage,componentClass);
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
