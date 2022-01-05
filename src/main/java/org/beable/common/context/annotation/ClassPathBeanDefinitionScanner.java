package org.beable.common.context.annotation;

import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.support.BeanDefinitionRegistry;
import org.beable.common.stereotype.Component;
import org.beable.common.utils.StringUtils;

import java.util.Set;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2022/1/5
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public void doScan(String... basePackages){
        for (String basePackage : basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates){
                // 解析 Bean的作用域
                String beanScope = resolveBeanScope(beanDefinition);
                if (StringUtils.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);
            }
        }
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StringUtils.isEmpty(value)){
            value = StringUtils.lowerFirstChar(beanClass.getSimpleName());
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getBeanClass();
        Scope scope = clazz.getAnnotation(Scope.class);
        if (scope == null){
            return StringUtils.EMPTY;
        }
        return scope.value();
    }
}
