package ru.samara.shop.repository.mock;

import ru.samara.shop.LoggerWrapper;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;

import java.util.List;

public class MockUserRepository implements UserRepository{
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserRepository.class);

    @Override
    public User save(User user) {
        LOG.info("Save" + user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("Delete" + id);
        return true;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String enail) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
