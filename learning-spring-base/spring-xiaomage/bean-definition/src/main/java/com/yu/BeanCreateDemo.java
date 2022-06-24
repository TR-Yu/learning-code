package com.yu;

import com.yu.beans.User;
import com.yu.factory.CreateFactoryInterface;
import com.yu.factory.DefaultUserBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.beans.factory.FactoryBean;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 1. xml配置的通过静态方法     xml配置的抽象工厂方式      xml配置的实现了BeanFactory的方式
 * 2. 基于serverLoaderBeanFactory   基于AutowireCapableBeanFactory的自动穿
 *
 * @author YU
 * @date 2022-06-03 0:34
 */
public class BeanCreateDemo {
    public static void main(String[] args) {
        implementOfCreateBeanByXml();
        implementOfCreateBeanByAutowire();

    }

    private static void implementOfCreateBeanByXml() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-create-context.xml");
        User user = xmlApplicationContext.getBean("user", User.class);
        System.out.println(user);

        User findUser = xmlApplicationContext.getBean("findUser", User.class);
        System.out.println(findUser);

        /**
         * 实现 {@link FactoryBean}内的方法，实现的 bean 的生成，注意还需要将具体实现的 xxxxxFactoryBean 注册到 xml中
         */
        User userBean = xmlApplicationContext.getBean("userFactoryBean", User.class);
        System.out.println(userBean);

        /**
         * 这种实现方式是什么呢：
         * 1. 采用java的spi机制：
         * 文件：既是在META-INF下创建service.xxxxxx接口文件，将实现这个接口的具体实现类路径放入
         * 2. spring 内有对java的spi机制的支持来获取到对应的serviceLoader(jdk下的对象) 并将其结合到spring内
         * 3. Spring内的关联的类是 org.springframework.beans.factory.serviceloader.AbstractServiceLoaderBasedFactoryBean
         * 4. 目前使用的 org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean 为其中一个实现类
         * 5. 对它进行xml的bean注入后，其属性serviceType使用对应接口接收。
         * 6. 在容器内获取到对应由他注入的对象，即可得到对应的接口的实现类
         */
        @SuppressWarnings("unchecked")
        ServiceLoader<CreateFactoryInterface> serviceLoaderBean = xmlApplicationContext.getBean("serviceLoaderBean", ServiceLoader.class);
        System.out.println("serverLoader =========");
        for (CreateFactoryInterface instance : serviceLoaderBean) {
            System.out.println(instance.findUser());
        }
        @SuppressWarnings("unchecked")
        List<CreateFactoryInterface> contextBeanList = (List<CreateFactoryInterface>) xmlApplicationContext.getBean("serviceLoaderBeanList");
        System.out.println("serverListFactory =========");
        contextBeanList.forEach(el -> System.out.println(el.findUser()));

        CreateFactoryInterface contextBeanFirst = (CreateFactoryInterface) xmlApplicationContext.getBean("serviceLoaderBeanFirst");
        System.out.println("serverFactory =========== \n" + contextBeanFirst.findUser());
        System.out.println("-------------------- line ----------------");
        jdkSpi();

    }

    /**
     * 根据查询的关系可以得到 {@link AutowireCapableBeanFactory} 是BeanFactory的一个子接口，
     * 具体的实现的是 调用其中的 {@link AbstractAutowireCapableBeanFactory#createBean(Class)} 方式进行注入，
     * 注意到范围是原型的。并不会加入到applicationContext的Bean的生命周期中去
     */
    private static void implementOfCreateBeanByAutowire() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultUserBeanFactory beanFactoryBean = autowireCapableBeanFactory.createBean(DefaultUserBeanFactory.class);
        System.out.println(beanFactoryBean.findUser());
    }

    /**
     * java的spi是将实现和接口分离；接口由调用方提供，实现由提供方提供具体的实现。在打成jar包的时候，需要在MATE-INF下 创建 sevice.xxxx接口的文件
     * 并且在文件内写入类全包名路径 xxx接口的实现；
     * 注意这种拓展机制，弊端是每一个接口都需要一个文件。
     */
    public static void jdkSpi() {
        ServiceLoader<CreateFactoryInterface> serviceLoader = ServiceLoader.load(CreateFactoryInterface.class);
        Iterator<CreateFactoryInterface> createFactory = serviceLoader.iterator();
        createFactory.forEachRemaining(el -> System.out.println(el.findUser()));
    }

}
