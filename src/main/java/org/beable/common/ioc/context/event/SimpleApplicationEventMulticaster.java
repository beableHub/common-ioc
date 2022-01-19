package org.beable.common.ioc.context.event;

import org.beable.common.ioc.beans.factory.BeanFactory;
import org.beable.common.ioc.context.ApplicationEvent;


/**
 * @author qing.wu
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }


    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)){
            // 目前为同步阻塞
            doInvokeListener(listener,event);
        }
    }

    private void doInvokeListener(ApplicationListener listener,ApplicationEvent event){
        listener.onApplicationEvent(event);
    }
}
