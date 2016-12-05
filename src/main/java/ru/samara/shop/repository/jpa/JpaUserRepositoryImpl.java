package ru.samara.shop.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author papont
 */
//     Инициализация JPA, если работать с родным Hibernate API
//    private SessionFactory sessionFactory;
//    private Session openSession() {
//        return sessionFactory.getCurrentSession();
//    }

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
//  1.    User ref = em.getReference(User.class, id);
//        em.remove(ref);
//
//
//  2. HQL = JPQL + Hibernate features
//    TypedQuery<User> query = em.createQuery("DELETE FROM User u WHERE u.id = :id", User.class);
//    return query.setParameter("id", id).executeUpdate() != 0;

// 3.named Query
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return (User) em.createNamedQuery(User.BY_EMAIL)
                .setParameter(1, email)
                .getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED)
                .getResultList();
    }
}
