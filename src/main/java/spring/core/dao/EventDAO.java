package spring.core.dao;


import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

import java.util.List;

public interface EventDAO {
    Event createEvent(EventCreationInformation eventCreationInformation) throws Exception;

    void deleteEvent(Long eventPk);

    Event searchEventByName(String eventName) throws Exception;

    List<Event> getAllEvents() throws Exception;

    List<ShowEvent> getAllShows() throws Exception;

    void addShowEvent(ShowEvent showEvent) throws Exception;
}
