package org.beable.common.ioc.beans.factory;

/**
 * @author qing.wu
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
