package com.thinking.in.spring.ioc.container;

import com.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanFactory作为IOC容器
 */
public class BeanFactoryAsIOCContainer {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        int beanDefinitions = reader.loadBeanDefinitions(location);
        // bean 加载的个数 3
        System.out.println("bean 加载的个数 " + beanDefinitions);

        lookupByType(beanFactory);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        // 根据类型查找SuperUser{address='上海'} User{id=1, name='张三'}
        System.out.println("根据类型查找" + user);
    }
}
