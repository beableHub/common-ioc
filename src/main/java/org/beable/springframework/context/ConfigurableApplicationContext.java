package org.beable.springframework.context;

import org.beable.springframework.beans.BeansException;

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
