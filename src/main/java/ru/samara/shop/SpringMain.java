package ru.samara.shop;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.samara.shop.repository.mock.MockUserRepository;
import ru.samara.shop.web.user.UserRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        //MockUserRepository mockUserRepository = (MockUserRepository) context.getBean("mockUserRepository");
        MockUserRepository mockUserRepository = context.getBean(MockUserRepository.class);


        UserRestController userRestController = context.getBean(UserRestController.class);

        //System.out.println(userRestController.toString());

        //mockUserRepository.get()

        context.close();
    }
}
