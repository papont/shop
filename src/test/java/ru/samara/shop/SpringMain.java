package ru.samara.shop;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.samara.shop.web.meal.UserMealRestController;

import java.util.Arrays;

/**
 * @author papont
 * @date 13.11.16.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml")){
            System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
            UserMealRestController userRestController = context.getBean(UserMealRestController.class);
            userRestController.delete(42);

        }
    }
}
