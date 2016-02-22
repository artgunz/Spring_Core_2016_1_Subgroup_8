package spring.core.service;

import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event create(EventCreationInformation creationInformation) throws Exception;

    void remove(Event event);

    Event getByName(String name) throws Exception;

    List<Event> getAll() throws Exception;

    List<Event> getForDateRange(Date fromDate, Date tillDate) throws Exception;

    List<ShowEvent> getNextEvents(Date tillDate) throws Exception;

    List<ShowEvent> getNextEventsByName(Date tillDate, String eventName) throws Exception;

    ShowEvent assignAuditoriumAndDate(Event event, Auditorium auditorium, Date date) throws Exception;
}
