package ru.samara.shop.serivice;

import ru.samara.shop.model.User;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    public User save(User user);

    public void delete(int id) throws NotFoundException;

    public User get(int id) throws NotFoundException;

    public User getByEmail(int id) throws NotFoundException;

    public List<User> getAll();

    public void update(User user) throws NotFoundException;
}
