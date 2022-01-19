package org.beable.common.ioc.context.annotation;

import org.beable.common.ioc.beans.factory.config.BeanDefinition;
import org.beable.common.ioc.stereotype.Component;
import org.beable.common.ioc.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qing.wu
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtils.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes){
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
