package ru.samara.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.samara.shop.LoggedUser;
import ru.samara.shop.model.User;


@RestController
@RequestMapping("/rest/profile")
public class UserRestController {

    @Autowired
    private UserHelper helper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(){
        return helper.get(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(){
        helper.delete(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user){
        helper.update(user);
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String testUTF() {
        return "Русский текст";
    }

//    public List<User> getAll(){
//        return helper.get(LoggedUser.id());
//
//        LOG.info("getAll");
//        return service.getAll();
//    }
//    public User create(User user){
//        LOG.info("create {}",user);
//        return service.save(user);
//    }
//
//    public void getByEmail(String email){
//        LOG.info("getByEmail {}", email);
//        service.getByEmail(email);
//    }


}
