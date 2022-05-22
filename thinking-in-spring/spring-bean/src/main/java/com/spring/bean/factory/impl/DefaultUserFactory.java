package com.spring.bean.factory.impl;

import com.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory , InitializingBean {


    @PostConstruct
    public void init(){
        System.out.println("PostConstruct UserFactory 初始化中");
    }

    @Override
    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory() : UserFactory 初始化中...");
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("使用InitializingBean#afterPropertiesSet 初始化userFactory");
    }
}
