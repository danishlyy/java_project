package com.spring.bean.factory.impl;

import com.spring.bean.factory.UserFactory;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory {


    @PostConstruct
    public void init(){
        System.out.println("PostConstruct 初始化中");
    }
}
