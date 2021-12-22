package org.beable.common.beans.factory;

import org.beable.common.beans.BeanException;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    Object getBean(String name,Object... args) throws BeanException;
}
