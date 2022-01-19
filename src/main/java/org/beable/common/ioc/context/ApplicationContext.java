package org.beable.common.ioc.context;

import org.beable.common.ioc.beans.factory.HierarchicalBeanFactory;
import org.beable.common.ioc.beans.factory.ListableBeanFactory;
import org.beable.common.ioc.core.io.ResourceLoader;

/**
 * @author qing.wu
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
