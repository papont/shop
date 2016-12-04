package ru.samara.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import ru.samara.shop.model.UserMeal;
import ru.samara.shop.repository.UserMealRepository;
import ru.samara.shop.util.exception.ExceptionUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author papont
 * @date 13.11.16.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMeal get(int id, int userId) {
        return ExceptionUtil.check(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.check(repository.delete(id, userId), id);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId){
        //return repository.getBetween(startDate, endDate.plus(1, ChronoUnit.DAYS), userId);
        return repository.getBetween(startDate, StringUtils.isEmpty(endDate) ? LocalDateTime.now() : endDate, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId){
        return repository.getAll(userId);
    }

    @Override
    public void deleteAll(int userId){
        repository.deleteAll(userId);
    }

    @Override
    public UserMeal save(UserMeal meal, int userId){
        return repository.save(meal, userId);
    }

    @Override
    public UserMeal update(UserMeal meal, int userId) {
        return ExceptionUtil.check(repository.save(meal, userId), meal.getId());
    }
}
