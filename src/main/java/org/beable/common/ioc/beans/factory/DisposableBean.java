package org.beable.common.ioc.beans.factory;

/**
 * @author qing.wu
 */
public interface DisposableBean {

    /**
     * 在销毁 bean 时由包含BeanFactory调用
     * @throws Exception
     */
    void destroy() throws Exception;
}
