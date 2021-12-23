package org.beable.common.beans.factory.xml;


import org.beable.common.beans.BeanException;
import org.beable.common.beans.PropertyValue;
import org.beable.common.beans.factory.config.BeanDefinition;
import org.beable.common.beans.factory.config.BeanReference;
import org.beable.common.beans.factory.support.AbstractBeanDefinitionReader;
import org.beable.common.beans.factory.support.BeanDefinitionRegistry;
import org.beable.common.core.io.Resource;
import org.beable.common.core.io.ResourceLoader;
import org.beable.common.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

/**
 * @author qing.wu
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        super(registry,resourceLoader);
    }


    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        }catch(Exception e){
            throw new BeanException("IOException parsing XML document from " + resource,e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        Document document = doLoadDocument(inputStream);
        Element root = document.getDocumentElement();
        doRegisterBeanDefinitions(root);
    }

    // need to be refactor TODO
    protected void doRegisterBeanDefinitions(Element root) throws ClassNotFoundException {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++){
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)){
                continue;
            }
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())){
                continue;
            }
            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取Class
            Class clazz = Class.forName(className);
            String beanName = StringUtils.isNotEmpty(id) ? id : name;
            if (StringUtils.isEmpty(beanName)){
                beanName = clazz.getSimpleName();
            }
            // 定义BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++){
                if (!(bean.getChildNodes().item(j) instanceof Element)){
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())){
                    continue;
                }
                // 解析标签
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值: 引人对象、值对象
                Object value = attrRef != "" ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }


    protected Document doLoadDocument(InputStream inputStream) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = createDocumentBuilderFactory();
        DocumentBuilder documentBuilder = createDocumentBuilder(documentBuilderFactory);
        return documentBuilder.parse(inputStream);
    }

    protected  DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory) throws ParserConfigurationException {
        return factory.newDocumentBuilder();
    }

    protected DocumentBuilderFactory createDocumentBuilderFactory(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(false);
        return factory;
    }
}
