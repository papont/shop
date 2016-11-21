package ru.samara.shop.web.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.util.exception.NotFoundException;
import ru.samara.shop.web.user.AdminRestController;

/**
 * @author papont
 * @date 21.11.16.
 */
@ContextConfiguration({
        //"classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-app.xml",
        //"classpath:spring/mock.xml"
        })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAdminSpringMockTest {

    @Autowired
    private AdminRestController controller;

    @Test
    public void testCreate() throws Exception {
        controller.create(new User(null, "Name", "email@ya.ru", "password", true, Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(7);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(0);
    }
}
