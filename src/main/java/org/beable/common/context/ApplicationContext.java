package org.beable.common.context;

import org.beable.common.beans.factory.HierarchicalBeanFactory;
import org.beable.common.beans.factory.ListableBeanFactory;
import org.beable.common.core.io.ResourceLoader;

/**
 * @author qing.wu
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
