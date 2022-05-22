package com.spring.bean.definite;

import com.spring.bean.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeanAliasDemo {

    public static void main(String[] args) {


        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/bean-definition-context.xml");
        User user = beanFactory.getBean("user", User.class);
        User aliasUser = beanFactory.getBean("aliasUser", User.class);
        // user 是否与 aliasUser 相等:true
        System.out.println("user 是否与 aliasUser 相等:" + (user == aliasUser));
    }
}
