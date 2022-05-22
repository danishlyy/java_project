package com.thinking.in.spring.ioc.dependency.injection;

import com.thinking.in.spring.ioc.domain.User;
import com.thinking.in.spring.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找和依赖注入不同源
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        // auto-wiring [User{id=1, name='张三'}, SuperUser{address='上海'} User{id=1, name='张三'}]
        // 手工配置Bean [SuperUser{address='上海'} User{id=1, name='张三'}, User{id=1, name='张三'}]
//        System.out.println(userRepository.getUsers());
        // org.springframework.beans.factory.support.DefaultListableBeanFactory@3a03464: defining beans [user,objectFactory,superUser,userRepository]; root of factory hierarchy
        System.out.println(userRepository.getBeanFactory());
        // false
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory<User> userObjectFactory = userRepository.getObjectFactory();
        // SuperUser{address='上海'} User{id=1, name='张三'} primary=true
        System.out.println(userObjectFactory.getObject());

        Environment bean = beanFactory.getBean(Environment.class);
        // 获取Environment 类型BeanStandardEnvironment {activeProfiles=[], defaultProfiles=[default],
        // propertySources=[PropertiesPropertySource {name='systemProperties'},
        // SystemEnvironmentPropertySource {name='systemEnvironment'}]}
        System.out.println("获取Environment 类型Bean" + bean);

    }
}
