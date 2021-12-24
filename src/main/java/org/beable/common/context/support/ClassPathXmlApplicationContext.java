package org.beable.common.context.support;


import org.beable.common.beans.BeansException;

/**
 * @author qing.wu
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext(){

    }

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException{
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }


    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    protected String[] getLocations() {
        return this.configLocations;
    }
}
