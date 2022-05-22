package com.thinking.in.spring.ioc.dependency.lookup;

import com.thinking.in.spring.ioc.annotation.Super;
import com.thinking.in.spring.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupRealTime(beanFactory);
        lookupLazyTime(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionsByType(beanFactory);
        lookupByAnnotation(beanFactory);

    }

    /**
     * 根据注解查找
     * @param beanFactory
     */
    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansWithAnnotation = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            // 根据注解查找{superUser=SuperUser{address='上海'} User{id=1, name='张三'}}
            System.out.println("根据注解查找" + beansWithAnnotation);
        }
    }

    /**
     * 根据类型查找集合
     * @param beanFactory
     */
    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            // 根据类型查找集合{user=User{id=1, name='张三'}}
            // 根据类型查找集合{user=User{id=1, name='张三'}, superUser=SuperUser{address='上海'} User{id=1, name='张三'}}
            System.out.println("根据类型查找集合" + userMap);
        }
    }

    /**
     * 根据类型查找
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        // 根据类型查找User{id=1, name='张三'}
        System.out.println("根据类型查找" + user);
    }

    /**
     * 根据名称查找
     * @param beanFactory
     */
    private static void lookupLazyTime(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        // lazy look up User{id=1, name='张三'}
        System.out.println("lazy look up " + user);
    }

    /**
     * 根据名称查找
     * @param beanFactory
     */
    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        // real time look upUser{id=1, name='张三'}
        System.out.println("real time look up" + user);
    }
}
