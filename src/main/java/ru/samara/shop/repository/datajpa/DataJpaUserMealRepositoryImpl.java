package ru.samara.shop.repository.datajpa;

import ru.samara.shop.model.UserMeal;
import ru.samara.shop.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author papont
 */
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return null;
    }

    @Override
    public void deleteAll(int userId) {

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
