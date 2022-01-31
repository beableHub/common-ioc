package org.beable.springframework.context;

import org.beable.springframework.beans.factory.HierarchicalBeanFactory;
import org.beable.springframework.beans.factory.ListableBeanFactory;
import org.beable.springframework.core.io.ResourceLoader;

/**
 * @author qing.wu
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
