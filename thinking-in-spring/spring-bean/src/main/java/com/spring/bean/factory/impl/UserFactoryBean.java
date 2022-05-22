package com.spring.bean.factory.impl;

import com.spring.bean.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link FactoryBean}
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
