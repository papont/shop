package ru.samara.shop.serivice;

import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by user on 13.07.2016.
 */
public class UserSeviceImpl implements UserService {

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
