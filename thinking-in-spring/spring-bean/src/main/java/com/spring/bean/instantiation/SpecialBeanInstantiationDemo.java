package com.spring.bean.instantiation;

import com.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的Bean实例化
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/special-bean-create-context.xml");
        serviceLoadDemo();
    }

    public static void serviceLoadDemo(){
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class,Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
