package ru.samara.shop.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.samara.shop.LoggerWrapper;
import ru.samara.shop.model.User;
import ru.samara.shop.service.UserService;

import java.util.List;


@Component
public class UserHelper {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserHelper.class);

    @Autowired
    private UserService service;

    public List<User> getAll(){
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id){
        LOG.info("get {}", id);
        return service.get(id);
    }

    public void delete(int id){
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id){
        LOG.info("update {}", user);
        if (user.isNew()) {
            user.setId(id);
        } else if (id != user.getId()) {
            throw LOG.getIllegalStateException(user + "don't updated");
        }
        service.update(user);
    }

    public User create(User user){
        LOG.info("create {}",user);
        return service.save(user);
    }

    public void getByEmail(String email){
        LOG.info("getByEmail {}", email);
        service.getByEmail(email);
    }

    public void enable(int id, boolean enable) {
        LOG.info("enable " + id);
        service.enable(id, enable);
    }
}

