package ru.samara.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;
import ru.samara.shop.util.exception.ExceptionUtil;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

/**
 * @author papont
 * @date 13.11.16.
 */
@Service
public class UserSeviceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    @Override
    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(user), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    public void evictCache() {

    }
}
