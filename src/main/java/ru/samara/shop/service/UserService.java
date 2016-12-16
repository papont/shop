package ru.samara.shop.service;

import ru.samara.shop.model.User;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    public User save(User user);

    public void delete(int id) throws NotFoundException;

    public User get(int id) throws NotFoundException;

    public User getByEmail(String email) throws NotFoundException;

    public List<User> getAll();

    public void update(User user) throws NotFoundException;

    public void evictCache();

    void enable(int id, boolean enable);
}
