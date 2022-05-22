package com.spring.bean.initialization;

import com.spring.bean.factory.UserFactory;
import com.spring.bean.factory.impl.DefaultUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 可配置的Bean
public class InitializationBeanPostConstructDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(InitializationBeanPostConstructDemo.class);
        annotationConfigApplicationContext.refresh();
        UserFactory userFactory = annotationConfigApplicationContext.getBean(UserFactory.class);
        annotationConfigApplicationContext.close();
    }


    @Bean
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
