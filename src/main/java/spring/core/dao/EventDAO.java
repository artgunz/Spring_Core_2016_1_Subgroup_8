package spring.core.dao;


import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

import java.util.List;

public interface EventDAO {
    Event createEvent(EventCreationInformation eventCreationInformation);

    void deleteEvent(Event event);

    Event searchEventByName(String eventName);

    List<Event> getAllEvents();

    List<ShowEvent> getAllShows();

    void addShowEvent(ShowEvent showEvent);
}
