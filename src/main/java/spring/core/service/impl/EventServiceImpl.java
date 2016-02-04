package spring.core.service.impl;

import spring.core.dao.EventDAO;
import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.ShowEvent;
import spring.core.service.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventDAO eventDAO;

    public Event create(final EventCreationInformation creationInformation) {
        return eventDAO.createEvent(creationInformation);
    }

    public void remove(final Event event) {
        eventDAO.deleteEvent(event);
    }

    public Event getByName(final String name) {
        return eventDAO.searchEventByName(name);
    }

    public List<Event> getAll() {
        return eventDAO.getAllEvents();
    }

    public List<Event> getForDateRange(final Date fromDate, final Date tillDate) {
        List<Event> listEvents = new ArrayList<Event>();

        for(ShowEvent event: eventDAO.getAllShows()){
            Date showTime = event.getShowTime();
            if( showTime.after(fromDate) && showTime.before(tillDate)){
                listEvents.add(event.getEvent());
            }
        }

        return listEvents;
    }

    public List<Event> getNextEvents(final Date tillDate) {
        Date current = new Date();
        List<Event> listEvents = new ArrayList<Event>();

        for(ShowEvent event: eventDAO.getAllShows()){
            Date showTime = event.getShowTime();
            if(showTime.after(current) && showTime.before(tillDate)){
                listEvents.add(event.getEvent());
            }
        }

        return listEvents;
    }

    public void assignAuditorium(final Event event, final Auditorium auditorium, final Date date) {
        ShowEvent foundedEvent = null;

        for(final ShowEvent searchEvent: eventDAO.getAllShows()){
            if(searchEvent.getEvent().equals(event) && searchEvent.getAuditorium().equals(auditorium) && searchEvent.getShowTime().equals(date)){
                foundedEvent = searchEvent;
                break;
            }
        }

        if(foundedEvent!=null){
            System.out.println("Event Already in base");
            return;
        }

        final ShowEvent showEvent = new ShowEvent(event, auditorium, date);

        eventDAO.addShowEvent(showEvent);
    }
}
