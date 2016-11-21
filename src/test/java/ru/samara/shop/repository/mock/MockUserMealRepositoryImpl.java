package ru.samara.shop.repository.mock;

import org.springframework.stereotype.Repository;
import ru.samara.shop.LoggerWrapper;
import ru.samara.shop.model.UserMeal;
import ru.samara.shop.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author papont
 * @date 13.11.16.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository{
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        LOG.info("Save" + userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("Delete" + id);
        return true;
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
