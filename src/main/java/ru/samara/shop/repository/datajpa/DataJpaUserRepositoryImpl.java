package ru.samara.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.samara.shop.Profiles;
import ru.samara.shop.model.BaseEntity;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;
import ru.samara.shop.util.exception.ValidationException;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author papont
 */
@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private ProxyUserRepository proxy;

    @Autowired
    private Environment env;

    private boolean mainUserModificationRestricted;

    @PostConstruct
    void postConstruct() {
        mainUserModificationRestricted = Arrays.asList(env.getActiveProfiles()).stream().filter(Profiles.HEROKU::equals).findFirst().isPresent();
    }

    public void checkModificationAllowed(Integer id) {
        if (mainUserModificationRestricted && id != null && id < BaseEntity.START_SEQ + 2) {
            throw new ValidationException("Admin/User modification is not allowed. <br><br><a class=\"btn btn-primary btn-lg\" role=\"button\" href=\"register\">Register &raquo;</a> your own please.");
        }
    }

    @Override
    public User save(User user) {
        return proxy.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public void enable(int id, boolean enabled) {
        checkModificationAllowed(id);
        proxy.enable(id, enabled);
    }

}
