package spring.core;

import spring.core.configuration.SpringConfiguration;
import spring.core.data.Auditorium;
import spring.core.data.Currency;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.Price;
import spring.core.data.Rating;
import spring.core.data.Seat;
import spring.core.data.ShowEvent;
import spring.core.data.TicketCreationInformation;
import spring.core.data.User;
import spring.core.data.UserRegistrationInformation;
import spring.core.data.UserTicket;
import spring.core.facade.UserFacade;
import spring.core.service.AuditoriumService;
import spring.core.service.BookingService;
import spring.core.service.EventService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class App
{
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    ApplicationContext context;

    public static void main( String[] args ) throws ParseException {
        App app = new App();
        app.init();

        Event event = app.registerEvent("DEADPOOL", 10.99, Rating.HIGH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date = simpleDateFormat.parse("14-03-2016 10:55:00");
        ShowEvent showEvent = app.registerShowEvent("DEADPOOL", "XXX", date);

        User user = app.registerUser("User", "email@email.ua");

        UserTicket userTicket = app.registerTicketForFirstAvailableTime(user, "DEADPOOL");

        BookingService bookingService = app.getContext().getBean(BookingService.class);
        bookingService.getTicketsForUser(user);
    }

    public void init(){
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
    }

    public Event registerEvent(String eventName, Double eventPrice, Rating eventRating){
        EventCreationInformation eventCreationInformation = new EventCreationInformation();
        eventCreationInformation.setRating(eventRating);
        eventCreationInformation.setBasePrice(new Price(Currency.USD, eventPrice));
        eventCreationInformation.setName(eventName);

        EventService eventService = context.getBean(EventService.class);

        Event event = eventService.create(eventCreationInformation);
        return event;
    }

    public ShowEvent registerShowEvent(String eventName, String auditoriumName, Date showTime){
        ShowEvent showEvent = null;

        EventService eventService = context.getBean(EventService.class);

        Event event =  null;
        for(int i=0; i<=10; i++){
            event  = eventService.getByName(eventName);
        }

        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        Auditorium auditorium = auditoriumService.searchAuditoriumByName(auditoriumName);

       showEvent = eventService.assignAuditoriumAndDate(event, auditorium, showTime);

        return showEvent;
    }

    public UserTicket registerTicketForFirstAvailableTime(User user, String eventName){
        EventService eventService = context.getBean(EventService.class);

        List<ShowEvent> showEventList = eventService.getNextEventsByName(new Date(), eventName);

        ShowEvent showEvent = showEventList.get(0);

        TicketCreationInformation ticketCreationInformation = new TicketCreationInformation();
        ticketCreationInformation.setShowEvent(showEvent);
        ticketCreationInformation.setSeat(new Seat(showEvent.getAuditorium(), 5));

        BookingService bookingService = context.getBean(BookingService.class);
        Price price = bookingService.getTicketPrice(ticketCreationInformation.getShowEvent().getEvent(),
                ticketCreationInformation.getShowEvent().getShowTime(),
                ticketCreationInformation.getSeat(), user);
        UserTicket userTicket = bookingService.bookTicket(user, price, ticketCreationInformation);

        return userTicket;
    }

    private User registerUser(String userName, String userEmail){
        UserFacade userFacade = context.getBean(UserFacade.class);

        UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
        userRegistrationInformation.setUserName(userName);
        userRegistrationInformation.setUserEmail(userEmail);

        User user = userFacade.register(userRegistrationInformation);

        return user;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(final ApplicationContext context) {
        this.context = context;
    }
}
