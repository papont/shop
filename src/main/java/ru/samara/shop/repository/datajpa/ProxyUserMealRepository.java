package ru.samara.shop.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.samara.shop.model.User;
import ru.samara.shop.model.UserMeal;
import ru.samara.shop.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @papont
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository  extends JpaRepository<UserMeal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    UserMeal save(UserMeal meal);

    @Query("SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    UserMeal get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id = :userId ORDER BY m.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal i WHERE i.user.id=:userId")
    void deleteAll(@Param("userId") int userId);

    @Query("SELECT m from UserMeal m WHERE m.user.id=:userId AND m.dateTime>=:after and m.dateTime<:before ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);
}
