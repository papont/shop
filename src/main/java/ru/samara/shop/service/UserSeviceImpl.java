package ru.samara.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.samara.shop.LoggedUser;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;
import ru.samara.shop.util.exception.ExceptionUtil;
import ru.samara.shop.util.exception.NotFoundException;

import java.util.List;

@Service("userService")
public class UserSeviceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
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
    @Transactional
    public void update(User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(user), user.getId());
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void evictCache() {
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void enable(int id, boolean enable) {
        repository.enable(id, enable);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + "is nor found");
        }
        return new LoggedUser(user);
    }
}
