package com.spring.bean.initialization;

import com.spring.bean.factory.UserFactory;
import com.spring.bean.factory.impl.DefaultUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration // 可配置的Bean
public class InitializationBeanPostConstructDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(InitializationBeanPostConstructDemo.class);
        annotationConfigApplicationContext.refresh();
        System.out.println("spring 上下文已经启动...");
        UserFactory userFactory = annotationConfigApplicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("spring 上下文准备关闭中...");
//        close方法调用，触发销毁
//        spring 上下文准备关闭中...
//        PreDestroy UserFactory 销毁中
//        DisposableBean#destroy 销毁中
//        自定义销毁方法 destroyUserFactory() : UserFactory 销毁中...
//        spring 上下文已关闭...
        annotationConfigApplicationContext.close();
        System.out.println("spring 上下文已关闭...");
    }

    /**
     * @Lazy(value=true) 使用后的输出结果  按需加载 触发依赖查找
     * spring 上下文已经启动...
     * PostConstruct UserFactory 初始化中
     * 使用InitializingBean#afterPropertiesSet 初始化userFactory
     * 自定义初始化方法 initUserFactory() : UserFactory 初始化中...
     * com.spring.bean.factory.impl.DefaultUserFactory@6328d34a
     * @Lazy(value=false) 使用后的输出结果
     * PostConstruct UserFactory 初始化中
     * 使用InitializingBean#afterPropertiesSet 初始化userFactory
     * 自定义初始化方法 initUserFactory() : UserFactory 初始化中...
     * spring 上下文已经启动...
     * com.spring.bean.factory.impl.DefaultUserFactory@7f9fcf7f
     *
     * 销毁执行顺序：
     * PreDestroy UserFactory 销毁中
     * DisposableBean#destroy 销毁中
     * 自定义销毁方法 destroyUserFactory() : UserFactory 销毁中...
     * @return
     */
    @Bean(initMethod = "initUserFactory",destroyMethod = "destroyUserFactory")
    @Lazy
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
