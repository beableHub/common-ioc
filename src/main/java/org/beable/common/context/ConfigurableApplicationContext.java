package org.beable.common.context;

import org.beable.common.beans.BeansException;

import java.io.Closeable;

/**
 * @author qing.wu
 */
public interface ConfigurableApplicationContext extends ApplicationContext, Closeable {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
