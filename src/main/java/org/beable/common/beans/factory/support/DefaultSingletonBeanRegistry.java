package org.beable.common.beans.factory.support;

import org.beable.common.beans.factory.DisposableBean;
import org.beable.common.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String,Object> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        this.singletonObjects.put(beanName,singletonObject);
    }


    public void registerDisposableBean(String beanName, DisposableBean disposableBean){
        synchronized (this.disposableBeans) {
            this.disposableBeans.put(beanName, disposableBean);
        }
    }

    public void destroySingletons(){
        String[] disposableBeanNames;
        synchronized (this.disposableBeans) {
            disposableBeanNames = this.disposableBeans.keySet().toArray(new String[0]);
        }
        for (int i = 0; i < disposableBeanNames.length; i++){
            destroySingleton(disposableBeanNames[i]);
        }
    }

    public void destroySingleton(String beanName){
        // Remove a registered singleton of the given name, if any.
        removeSingleton(beanName);

        DisposableBean disposableBean;
        synchronized (this.disposableBeans) {
            disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
        }
        destroyBean(beanName,disposableBean);
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletonObjects){
            singletonObjects.remove(beanName);
        }
    }

    protected void destroyBean(String beanName, DisposableBean bean) {
        if (bean != null){
            try {
                bean.destroy();
            }catch (Exception e){
                // TODO
            }
        }
    }
}
