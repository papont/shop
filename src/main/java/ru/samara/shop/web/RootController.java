package ru.samara.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
    private static final String MEALS = "mealList";
    public static final String USER="userList";

    @RequestMapping(value="/meals", method= RequestMethod.GET)
    public String mealList(Model model) {
        return MEALS;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        return USER;
    }

}
