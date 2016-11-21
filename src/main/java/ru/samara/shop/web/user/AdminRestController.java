package ru.samara.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.service.UserService;

/**
 * @author papont
 * @date 13.11.16.
 */
@Controller
public class AdminRestController {

    @Autowired
    private UserService service;

    public void delete(int id) {
        service.delete(id);
    }

    public void create(User user) {

    }
}
