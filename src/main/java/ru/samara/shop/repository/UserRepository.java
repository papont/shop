package ru.samara.shop.repository;

import ru.samara.shop.model.User;

import java.util.List;

/**
 * Created by user on 13.07.2016.
 */
public interface UserRepository {
    User save(User user);

    // false is not found
    boolean delete(int id);

    // null is not found
    User get(int id);

    // null is not found
    User getByEmail(String enail);

    List<User> getAll();
}
