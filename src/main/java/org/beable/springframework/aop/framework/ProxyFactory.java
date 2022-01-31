package org.beable.springframework.aop.framework;

import org.beable.springframework.aop.AdvisedSupport;

/**
 * @author qing.wu
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }
    
    public AopProxy createAopProxy(){
        // TODO cglib
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
