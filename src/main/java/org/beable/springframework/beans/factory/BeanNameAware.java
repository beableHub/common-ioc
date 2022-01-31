package org.beable.springframework.beans.factory;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/26
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
