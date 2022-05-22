package com.spring.bean.factory;

import com.spring.bean.domain.User;

/**
 * User工厂
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

    void initUserFactory();
}
