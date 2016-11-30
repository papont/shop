package ru.samara.shop.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.samara.shop.model.User;
import ru.samara.shop.model.UserMeal;
import ru.samara.shop.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author papont
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);

        if (userMeal.isNew()) {
            em.persist(userMeal);
        } else {
            if (get(userMeal.getId(), userId) == null) return null;
            em.merge(userMeal);
/*
            if (em.createNamedQuery(UserMeal.UPDATE)
                    .setParameter("datetime", userMeal.getDateTime())
                    .setParameter("calories", userMeal.getCalories())
                    .setParameter("desc", userMeal.getDescription())
                    .setParameter("id", userMeal.getId())
                    .setParameter("userId", userId).executeUpdate() == 0) {
                return null;
            }
*/
        }
        return userMeal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> userMeals = em.createNamedQuery(UserMeal.GET, UserMeal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return userMeals.size() == 0 ? null : DataAccessUtils.requiredSingleResult(userMeals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteAll(int userId) {
        em.createNamedQuery(UserMeal.DELETE_ALL).setParameter("userId", userId).executeUpdate();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter("userId", userId)
                .setParameter("after", startDate)
                .setParameter("before", endDate).getResultList();
    }
}
