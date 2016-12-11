package ru.samara.shop.web.mock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.samara.shop.UserTestData;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.util.exception.NotFoundException;
import ru.samara.shop.web.user.AdminRestController;

import java.util.Arrays;

import static ru.samara.shop.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/mock.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileMockTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-mvc.xml", "spring/spring-app.xml", "spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }


//    @Test
//    public void testCreate() throws Exception {
////        controller.create(new User(null, "Name", "email@ya.ru", "password", true, Role.ROLE_USER));
//        controller.create(new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN));
//
//    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(7);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(0);
    }
}
