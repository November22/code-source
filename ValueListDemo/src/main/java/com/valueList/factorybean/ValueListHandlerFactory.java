package com.valueList.factorybean;

import net.mlw.vlh.DefaultValueListHandlerImpl;
import net.mlw.vlh.ValueListHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iths
 * @date 2018/4/10.
 */
public class ValueListHandlerFactory implements FactoryBean {

    private ValueListHandler valueListHandler;

    public Object getObject() throws Exception {
        return valueListHandler;
    }

    public void setAdapterConfigLocations(Resource[] resources){
        //BeanDefinitionRegistry：向注册表中注册BeanDefinition实例
        BeanDefinitionRegistry definitionRegistry = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(definitionRegistry);
        for(Resource resource:resources){
            reader.loadBeanDefinitions(resource);
        }
        BeanFactory factory = (BeanFactory) definitionRegistry;
        Map adapters = new HashMap();
        for(String beanName:definitionRegistry.getBeanDefinitionNames()){
            //isTypeMatch:判断当前bean工厂中，bean是否属于map类型
            if(factory.isTypeMatch(beanName,Map.class)){
//                Map mapTest = ((DefaultListableBeanFactory) definitionRegistry).getBean(beanName, Map.class);
                Map map = factory.getBean(beanName, Map.class);
                adapters.putAll(map);
            }
        }
        DefaultValueListHandlerImpl defaultValueListHandler = new DefaultValueListHandlerImpl();
        defaultValueListHandler.getConfig().setAdapters(adapters);
        valueListHandler = defaultValueListHandler;
    }

    public Class<?> getObjectType() {
        return ValueListHandler.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
