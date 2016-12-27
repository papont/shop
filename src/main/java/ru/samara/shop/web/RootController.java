package ru.samara.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import ru.samara.shop.LoggedUser;
import ru.samara.shop.model.User;
import ru.samara.shop.service.UserService;
import ru.samara.shop.util.PasswordUtil;
import ru.samara.shop.util.UserTo;
import ru.samara.shop.web.user.UserHelper;

import javax.validation.Valid;

@Controller
public class RootController {
    private static final String MEALS = "mealList";
    public static final String USER="userList";
    public static final String PROFILE="profile";


    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String root() {
        return "redirect:users";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);

        return "login";
    }

    @RequestMapping(value="/profile", method= RequestMethod.POST)
    public String saveProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.put("userTo", LoggedUser.get().getUserTo());
            return PROFILE;
        } else {
            status.setComplete();
            userService.save(UserHelper.updateUser(LoggedUser.get().getUser(), userTo));
            return "redirect:users";
        }
    }

//    @RequestMapping(value="/profile", method= RequestMethod.GET)
//    public String profile(ModelMap model) {
//        UserTo userTo = LoggedUser.get().getUserTo();
//        model.put("userTo", userTo);
//        return PROFILE;
//    }
//
//    public static User updateUser(User oldUser, UserTo userTo) {
//        PasswordUtil.getEncoded(userTo);
//        oldUser.setName(userTo.getName());
//        oldUser.setEmail(userTo.getEmail().toLowerCase());
//        oldUser.setPassword(userTo.getPassword());
//        return oldUser;
//    }


    @RequestMapping(value="/meals", method= RequestMethod.GET)
    public String mealList(Model model) {
        return MEALS;
    }

    //@Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        return USER;
    }


}
