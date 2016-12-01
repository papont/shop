package ru.samara.shop.repository.datajpa;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;

import java.util.List;

/**
 * @author papont
 */
public class DataJpaUserRepositoryImpl extends JpaRepository<User, Integer> implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
