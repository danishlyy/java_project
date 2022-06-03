package com.spring.bean.definite;

import com.spring.bean.factory.UserFactory;
import com.spring.bean.factory.impl.DefaultUserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //注册外部单例Bean
        beanFactory.registerSingleton("userFactory",userFactory);
        //启动spring应用上下文
        applicationContext.refresh();
        UserFactory factory = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("factory == userFactory "+ (userFactory == factory));

    }
}
