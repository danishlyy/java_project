package com.spring.bean.definite;

import com.spring.bean.domain.User;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 3、通过@Import 导入
 */
@Import(value = AnnotationDefinitionBeanDemo.Config.class)
public class AnnotationDefinitionBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(AnnotationDefinitionBeanDemo.class);
        // 非命名 Bean 注册
        registerBeanDefinition(annotationConfigApplicationContext,User.class);
        // 命名 Bean注册
        registerBeanDefinition(annotationConfigApplicationContext,"sh_user",User.class);
        annotationConfigApplicationContext.refresh();
        // Config 类型所有的Beans {com.spring.bean.definite.AnnotationDefinitionBeanDemo$Config=com.spring.bean.definite.AnnotationDefinitionBeanDemo$Config@37374a5e}
        System.out.println("Config 类型所有的Beans " + annotationConfigApplicationContext.getBeansOfType(Config.class));
        // User 类型所有的Beans {com.spring.bean.domain.User#0=User{id=1, name='上海'}, sh_user=User{id=1, name='上海'}, user=User{id=4, name='北蔡'}}
        System.out.println("User 类型所有的Beans " + annotationConfigApplicationContext.getBeansOfType(User.class));
        // 显示关闭Spring上下文
        annotationConfigApplicationContext.close();

    }


    /**
     * 命名 Bean 注册方式
     * @param beanDefinitionRegistry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,String beanName,Class<?> beanClass){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder
                .addPropertyValue("id",1L)
                .addPropertyValue("name","上海");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinition);
    }

    /**
     * 非命名 Bean 注册方式
     * @param beanDefinitionRegistry
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,Class<?> beanClass){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder
                .addPropertyValue("id",1L)
                .addPropertyValue("name","上海");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,beanDefinitionRegistry);
    }
    // 2、通过@Component进行定义
    @Component
    public static class Config {
        // 1、通过@Bean方式定义
        @Bean(name = {"user","smallUser"})
        public User user(){
            User user = new User();
            user.setId(4L);
            user.setName("北蔡");
            return user;
        }
    }
}
