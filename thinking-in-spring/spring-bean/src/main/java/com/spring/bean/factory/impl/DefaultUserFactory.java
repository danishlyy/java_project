package com.spring.bean.factory.impl;

import com.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory , InitializingBean, DisposableBean {


    @PostConstruct
    public void init(){
        System.out.println("PostConstruct UserFactory 初始化中");
    }

    @PreDestroy
    public void destroyMethod(){
        System.out.println("PreDestroy UserFactory 销毁中");
    }

    @Override
    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory() : UserFactory 初始化中...");
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("使用InitializingBean#afterPropertiesSet 初始化userFactory");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy 销毁中");
    }

    @Override
    public void destroyUserFactory() {
        System.out.println("自定义销毁方法 destroyUserFactory() : UserFactory 销毁中...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("DefaultUserFactory 正在被进行垃圾回收");
    }
}
