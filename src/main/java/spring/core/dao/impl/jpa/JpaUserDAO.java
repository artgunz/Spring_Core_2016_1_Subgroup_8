package spring.core.dao.impl.jpa;

import spring.core.dao.UserDAO;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.data.UserStatistic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("dev")
@Transactional
public class JpaUserDAO implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(final UserRegistrationInformation registrationInformation) { //TODO add bulder with
        User user = new User();
        user.setName(registrationInformation.getUserName());
        user.setEmail(registrationInformation.getUserEmail());
        user.setBirthDate(registrationInformation.getBirthDate());

        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteUser(final Long userId) {
        entityManager.remove(entityManager.find(User.class, userId));
    }

    @Override
    public User searchUserByEmail(final String userEmail) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from User u where u.email = :userEmail";

        return (User) session.createQuery(hql)
                .setParameter("userEmail", userEmail)
                .uniqueResult();
    }

    @Override
    public User searchUserByPk(final Long userPk) {
        return entityManager.find(User.class, userPk);
    }

    @Override
    public List<User> searchUsersByName(final String userName) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from User u where u.name = :userName";

        return session.createQuery(hql)
                .setParameter("userName", userName)
                .list();
    }

    public void addUserStats(User user, UserStatistic userStatistic){
        entityManager.getTransaction().begin();

        userStatistic.setUser(user);
        entityManager.persist(userStatistic);
        user.setUserStatistic(userStatistic);

        entityManager.getTransaction().commit();
    }

    public void updateUserStats(User user, UserStatistic userStatistic){
        entityManager.getTransaction().begin();

        userStatistic.setUser(user);
        entityManager.persist(userStatistic);
        user.setUserStatistic(userStatistic);

        entityManager.getTransaction().commit();

    }
}
