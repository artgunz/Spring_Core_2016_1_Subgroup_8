package spring.core.dao.impl.jpa;

import spring.core.dao.EventDAO;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

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
public class JpaEventDAO implements EventDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event createEvent(final EventCreationInformation eventCreationInformation) throws Exception {
        Event event = new Event(eventCreationInformation.getName(),
                eventCreationInformation.getBasePrice(), eventCreationInformation.getRating());
        entityManager.persist(event);
        return event;
    }

    @Override
    public void deleteEvent(final Long eventPk) {
        entityManager.remove(entityManager.find(Event.class, eventPk));
    }

    @Override
    public Event searchEventByName(final String eventName) throws Exception {
        HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
        Session session = hem.getSession();

        String hql = "from Event e where e.name = :eventName";

        return (Event) session.createQuery(hql)
                .setParameter("eventName", eventName)
                .uniqueResult();
    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM Event e");
        return (List<Event>) query.getResultList();
    }

    @Override
    public List<ShowEvent> getAllShows() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM ShowEvent e");
        return (List<ShowEvent>) query.getResultList();
    }

    @Override
    public void addShowEvent(final ShowEvent showEvent) throws Exception {
        entityManager.persist(showEvent);
    }
}
