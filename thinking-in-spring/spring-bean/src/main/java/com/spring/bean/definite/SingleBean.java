package com.spring.bean.definite;

import com.spring.bean.factory.UserFactory;
import com.spring.bean.factory.impl.DefaultUserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 如何注册一个Spring Bean
 * 1、使用 BeanDefinitionRegistry
 * 2、外部单体对象来注册
 */
public class SingleBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory",userFactory);
        applicationContext.refresh();

        UserFactory userFactoryLookup = beanFactory.getBean("userFactory", UserFactory.class);
        // userFactory == userFactoryLookup true
        System.out.println("userFactory == userFactoryLookup " + (userFactory == userFactoryLookup));
        applicationContext.close();
    }
}
