package spring.core.service;

import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EventService {

    Event create(EventCreationInformation creationInformation);

    void remove(Event event);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(Date fromDate, Date tillDate);

    List<Event> getNextEvents(Date tillDate);

    void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
