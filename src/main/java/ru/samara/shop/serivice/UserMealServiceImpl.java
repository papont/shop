package ru.samara.shop.serivice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.shop.model.User;

import ru.samara.shop.repository.UserMealRepository;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

/**
 * @author papont
 * @date 13.11.16.
 */
@Service
public class UserMealServiceImpl implements UserService {

    @Autowired
    private UserMealRepository repository;


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
