package ru.samara.shop;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.samara.shop.web.meal.UserMealHelper;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
//        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml")){
          try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
              ctx.getEnvironment().setActiveProfiles(Profiles.POSTGRES);
              ctx.load("spring/spring-app.xml", "spring/mock.xml");
              ctx.refresh();

              System.out.println("\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n");
              UserMealHelper userRestController = ctx.getBean(UserMealHelper.class);
              userRestController.delete(42);

        }
    }
}
