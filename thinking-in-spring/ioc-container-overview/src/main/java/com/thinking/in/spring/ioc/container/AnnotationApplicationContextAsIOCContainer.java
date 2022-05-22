package com.thinking.in.spring.ioc.container;

import com.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * {@link org.springframework.context.ApplicationContext} 作为IOC容器
 */
public class AnnotationApplicationContextAsIOCContainer {

    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIOCContainer.class);
        applicationContext.refresh();
        lookupCollectionsByType(applicationContext);


    }

    /**
     * 利用Java注解定义Bean
     * @return
     */
    @Bean
    public User user(){
        User user = new User();
        user.setId(2L);
        user.setName("张三");
        return user;
    }

    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            // 增加 @Bean User 定义根据类型查找集合{user=User{id=2, name='张三'}}
            System.out.println("根据类型查找集合" + userMap);
        }
    }
}
