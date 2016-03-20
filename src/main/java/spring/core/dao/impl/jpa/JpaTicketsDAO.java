package spring.core.dao.impl.jpa;

import spring.core.dao.TicketsDAO;
import spring.core.data.Event;
import spring.core.data.Ticket;
import spring.core.data.User;
import spring.core.data.UserTicket;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("dev")
@Transactional
public class JpaTicketsDAO implements TicketsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket addTicket(final User user, final Ticket ticket) {
        entityManager.persist(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets(final Event event, final Date date) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from UserTicket ut where ut.event = :event and ut.showTime = :date";

        return session.createQuery(hql)
                .setParameter("event", event)
                .setParameter("date", date)
                .list();
    }

    @Override
    public List<Ticket> getTicketsForUser(final User user) {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from UserTicket ut where ut.user = :user";

        return session.createQuery(hql)
                .setParameter("user", user)
                .list();
    }
}
