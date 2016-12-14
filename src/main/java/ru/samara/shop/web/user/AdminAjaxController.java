package ru.samara.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
//import ru.samara.shop.UserTo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController  {

    @Autowired
    private  UserHelper helper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return helper.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return helper.get(id);
    }

//    @RequestMapping(value = "/{id}/enable", method = RequestMethod.POST)
//    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
//        helper.enable(id, enabled);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        helper.delete(id);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestParam("id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password) {
        User user = new User(id, name, email, password, true, Role.ROLE_USER);
        if (id == 0) {
            helper.create(user);
        } else {
            helper.update(user, id);
        }

    }

//    @RequestMapping(method = RequestMethod.POST)
//    public void createOrUpdate(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
//        if (result.hasErrors()) {
//            throw LOG.getValidationException(result);
//        } else {
//            status.setComplete();
//            if (userTo.getId() == 0) {
//                create(userTo);
//            } else {
//                update(userTo, userTo.getId());
//            }
//        }
//    }
}
