package ru.samara.shop.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.samara.shop.model.User;
import ru.samara.shop.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
//@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
//  1.    User ref = em.getReference(User.class, id);
//        em.remove(ref);
//
//
//  2.
    TypedQuery<User> query = em.createQuery("DELETE FROM User u WHERE u.id = :id", User.class);
    return query.setParameter("id", id).executeUpdate() != 0;

//        return em.createNamedQuery(User.DELETE)
//                .setParameter("id", id)
//                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
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
