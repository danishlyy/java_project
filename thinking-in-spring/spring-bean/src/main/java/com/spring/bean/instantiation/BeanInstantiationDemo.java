package com.spring.bean.instantiation;

import com.spring.bean.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/bean-create-context.xml");
        // 通过静态方法进行实例化User
        User user = beanFactory.getBean("user-by-static-method", User.class);
        // User{id=4, name='陆家嘴'}
        System.out.println(user);
        User userInstance = beanFactory.getBean("user-by-instance-method", User.class);
        // User{id=4, name='陆家嘴'}
        System.out.println(userInstance);
        User userFactoryBean = beanFactory.getBean("userFactoryBean", User.class);
        // User{id=4, name='陆家嘴'}
        System.out.println(userFactoryBean);
        // false
        System.out.println(userInstance == user);
        // false
        System.out.println(userFactoryBean == user);
    }
}
