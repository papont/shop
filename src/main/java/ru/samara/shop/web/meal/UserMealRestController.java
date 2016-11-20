package ru.samara.shop.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.samara.shop.serivice.UserMealServiceImpl;

/**
 * @author papont
 * @date 13.11.16.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealServiceImpl service;
}
