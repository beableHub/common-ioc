package org.beable.common.beans.factory.support;

import org.beable.common.beans.factory.DisposableBean;
import org.beable.common.beans.factory.ObjectFactory;
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

    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存 普通对象
     */
    private final Map<String,Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存，提前暴露对象，没有完全实例化对象
     */
    private final Map<String,Object> earlySingletonObjects = new HashMap<>();

    /**
     * 三级缓存，存放代理对象
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String,Object> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        synchronized (this.singletonObjects) {
            Object singleObject = singletonObjects.get(beanName);
            if (null == singleObject) {
                singleObject = earlySingletonObjects.get(beanName);
                // 判断二级缓存里面是否有对象
                if (null == singleObject) {
                    ObjectFactory objectFactory = singletonFactories.get(beanName);
                    if (objectFactory != null) {
                        singleObject = objectFactory.getObject();
                        // 把三级缓存里面的代理对象里面的真实对象取出，放入二级缓存中
                        earlySingletonObjects.put(beanName, singleObject);
                        singletonFactories.remove(beanName);
                    }
                }
            }
            return singleObject;
        }
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects){
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            addSingleton(beanName, singletonObject);
        }
    }

    protected void addSingleton(String beanName,Object singletonObject){
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    protected void addSingletonFactory(String beanName, ObjectFactory singletonFactory){
        synchronized (this.singletonObjects){
            if (!this.singletonObjects.containsKey(beanName)){
                this.singletonFactories.put(beanName,singletonFactory);
                this.earlySingletonObjects.remove(beanName);
                this.singletonObjects.remove(beanName);
            }
        }
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
            this.singletonObjects.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.singletonFactories.remove(beanName);
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
