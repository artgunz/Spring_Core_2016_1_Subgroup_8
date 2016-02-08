package spring.core.service;

import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event create(EventCreationInformation creationInformation);

    void remove(Event event);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(Date fromDate, Date tillDate);

    List<ShowEvent> getNextEvents(Date tillDate);

    List<ShowEvent> getNextEventsByName(Date tillDate, String eventName);

    ShowEvent assignAuditoriumAndDate(Event event, Auditorium auditorium, Date date);
}
