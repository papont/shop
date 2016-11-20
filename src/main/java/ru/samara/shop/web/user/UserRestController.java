package ru.samara.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.samara.shop.serivice.UserService;

@Controller
public class UserRestController {

    @Autowired
    private UserService service;

}
