package ru.samara.shop.serivice;

import org.springframework.beans.factory.annotation.Autowired;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

public class UserSeviceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public User get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public User getByEmail(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) throws NotFoundException {

    }
}
