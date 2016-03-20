package spring.core.dao.impl.jpa;

import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.User;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("dev")
@Transactional
public class JpaAuditoriumDAO implements AuditoriumDAO {
    private static final Logger LOGGER = LogManager.getLogger(JpaAuditoriumDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AuditoriumLoader auditoriumLoader;

    @Override
    public Auditorium searchAuditoriumByName(final String name) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from Auditorium a where a.name = :name";

        return (Auditorium) session.createQuery(hql)
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        Query query = entityManager.createQuery("SELECT a FROM Auditorium a");
        return (List<Auditorium>) query.getResultList();
    }

    @Override
    public Auditorium addAuditorium(final Auditorium auditorium) {
        entityManager.persist(auditorium);
        return auditorium;
    }
}
