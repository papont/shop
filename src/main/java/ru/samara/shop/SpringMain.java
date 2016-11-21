package ru.samara.shop;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.samara.shop.web.user.AdminRestController;

import java.util.Arrays;

/**
 * @author papont
 * @date 13.11.16.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml")){
            System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

            //MockUserRepository mockUserRepository = (MockUserRepository) context.getBean("mockUserRepository");
            //MockUserRepository mockUserRepository = context.getBean(MockUserRepository.class);
            //UserRestController userRestController = context.getBean(UserRestController.class);

            AdminRestController adminRestController = context.getBean(AdminRestController.class);
//            adminUserRestController.delete(42);

        }
    }
}
