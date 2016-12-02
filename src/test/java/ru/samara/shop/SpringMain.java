package ru.samara.shop;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samara.shop.web.meal.UserMealRestController;

import java.util.Arrays;

/**
 * @author papont

 */
public class SpringMain {
    public static void main(String[] args) {
//        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml")){
          try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
              ctx.getEnvironment().setActiveProfiles("postgres");
              ctx.load("spring/spring-app.xml", "spring/spring-db.xml");
              ctx.refresh();

              System.out.println("\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n");
              UserMealRestController userRestController = ctx.getBean(UserMealRestController.class);
              userRestController.delete(42);

        }
    }
}
