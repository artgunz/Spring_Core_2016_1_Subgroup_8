package spring.core.dao.impl.map;

import spring.core.dao.EventDAO;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;
import spring.core.populator.Populator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("hashMapEventDAO")
public class HashMapEventDAOImpl implements EventDAO {
    private final static Set<Event> eventStorage = new HashSet<Event>();
    private final static Set<ShowEvent> showEventStorage = new HashSet<ShowEvent>();

    @Autowired
    @Qualifier("eventRegistrationPopulator")
    Populator<EventCreationInformation, Event> eventRegistrationPopulator;

    public Event createEvent(final EventCreationInformation eventCreationInformation) {
        final Event event = new Event(eventCreationInformation.getName(), eventCreationInformation.getBasePrice(), eventCreationInformation.getRating());
        eventRegistrationPopulator.populate(eventCreationInformation, event);

        return event;
    }

    public void deleteEvent(final Event event) {
        if (event != null) {
            eventStorage.remove(event);
        }

    }

    public Event searchEventByName(final String eventName) {
        for (final Event event : eventStorage) {
            if (event.getName().equals(eventName)) {
               return  event;
            }
        }

        return null;
    }

    public List<Event> getAllEvents() {
        final List<Event> mainList = new ArrayList<Event>();
        mainList.addAll(eventStorage);
        return mainList;
    }

    public List<ShowEvent> getAllShows() {
        final List<ShowEvent> mainList = new ArrayList<>();
        mainList.addAll(showEventStorage);
        return mainList;
    }

    @Override
    public void addShowEvent(ShowEvent showEvent) {
        showEventStorage.add(showEvent);
    }
}
