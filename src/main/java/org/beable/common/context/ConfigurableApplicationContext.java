package org.beable.common.context;

import org.beable.common.beans.BeansException;

/**
 * @author qing.wu
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
