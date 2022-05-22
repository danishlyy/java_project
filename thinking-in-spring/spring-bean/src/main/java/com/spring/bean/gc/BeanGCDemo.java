package com.spring.bean.gc;


import com.spring.bean.factory.UserFactory;
import com.spring.bean.initialization.InitializationBeanPostConstructDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 在ApplicationContext关闭之前，GC是不会回收Bean的，纵然显示的调用也是如此。而在ApplicationContext关闭之后，JVM会在垃圾回收周期中去回收掉Bean
 */
public class BeanGCDemo {

    /**
     * PostConstruct UserFactory 初始化中
     * 使用InitializingBean#afterPropertiesSet 初始化userFactory
     * 自定义初始化方法 initUserFactory() : UserFactory 初始化中...
     * PreDestroy UserFactory 销毁中
     * DisposableBean#destroy 销毁中
     * 自定义销毁方法 destroyUserFactory() : UserFactory 销毁中...
     * spring 应用上下文已关闭
     * DefaultUserFactory 正在被进行垃圾回收
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InitializationBeanPostConstructDemo.class);

        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        applicationContext.close();
        System.out.println("spring 应用上下文已关闭");
        Thread.sleep(10000L);
        userFactory = null;
        System.gc();
        Thread.sleep(10000L);
    }
}
