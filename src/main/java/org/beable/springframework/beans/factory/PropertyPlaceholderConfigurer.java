package org.beable.springframework.beans.factory;

import org.beable.springframework.beans.BeansException;
import org.beable.springframework.beans.PropertyValue;
import org.beable.springframework.beans.PropertyValues;
import org.beable.springframework.beans.factory.config.BeanDefinition;
import org.beable.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.beable.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.beable.springframework.core.io.DefaultResourceLoader;
import org.beable.springframework.core.io.Resource;
import org.beable.springframework.util.StringValueResolver;

import java.util.Properties;

/**
 * @author qing.wu
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";


    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try{
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames){
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()){
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)){
                        continue;
                    }
                    value = resolvePlaceholder((String) value,properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),value));
                }
            }

            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        }catch (Exception e){
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceholder(String strVal, Properties properties) {
        StringBuilder buffer = new StringBuilder(strVal);
        int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex){
            String propKey = strVal.substring(startIndex + 2,stopIndex);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIndex,stopIndex + 1, propVal);
        }
        return buffer.toString();
    }


    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver{

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties){
            this.properties = properties;
        }
        @Override
        public String resolveStringValue(String value) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(value,properties);
        }
    }

}
