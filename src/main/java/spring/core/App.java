package spring.core;

import spring.core.configuration.RootSpringConfiguration;
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
import spring.core.exception.EventAlreadyExistsException;
import spring.core.facade.UserFacade;
import spring.core.service.AuditoriumService;
import spring.core.service.BookingService;
import spring.core.service.EventService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    ApplicationContext context;

    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        App app = new App();
        app.init();

        String ratings[] = new String[]{"HIGH", "LOW", "MID"};

        String eventsNames[] = new String[]{"XXX", "DEADPOOL", "WALKING DEAD", "COMEDY"};
        Double pricesEvents[] = new Double[]{2.55, 3.55, 5.55, 8.66};

        String eventsShowDates[] = new String[]{
                "18-05-2016 10:59:00",
                "14-07-2016 10:53:00",
                "14-08-2016 10:55:00",
                "14-09-2016 10:56:00",
                "14-10-2016 10:57:00",
                "14-11-2016 10:54:00",
                "14-12-2016 10:51:00"};

        String auditoriums[] = app.getAuditoriums();

        String users[] = new String[]{
                "Vasia",
                "Artem",
                "Oleg",
                "Onatoley"};

        int iterations = 10;

        for (int i = 0; i <= iterations; i++) {
            int idx = new Random().nextInt(ratings.length);
            String randomRating = (ratings[idx]);
            Rating rating = Rating.valueOf(randomRating);

            idx = new Random().nextInt(eventsNames.length);
            String randomEventName = (eventsNames[idx]);
            Double randomPriceEvent = (pricesEvents[idx]);

            Event event = app.registerEvent(randomEventName, randomPriceEvent, rating);

            idx = new Random().nextInt(eventsShowDates.length);
            String eventShowDateRandom = (eventsShowDates[idx]);
            Date eventShowDate = simpleDateFormat.parse(eventShowDateRandom);

            idx = new Random().nextInt(auditoriums.length);
            String auditorium = (auditoriums[idx]);

            ShowEvent showEvent = app.registerShowEvent(randomEventName, auditorium, eventShowDate);

            idx = new Random().nextInt(users.length);
            String userAsString = (users[idx]);

            User user = app.registerUser(userAsString, String.format("%s@email.com", userAsString.toLowerCase()));

            UserTicket userTicket = app.registerTicketForFirstAvailableTime(user, randomEventName);

            BookingService bookingService = app.getContext().getBean(BookingService.class);
            bookingService.getTicketsForUser(user);
        }
    }

    public void init() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        context = new AnnotationConfigApplicationContext(RootSpringConfiguration.class);
    }

    public Event registerEvent(String eventName, Double eventPrice, Rating eventRating) throws Exception {
        EventCreationInformation eventCreationInformation = new EventCreationInformation();
        eventCreationInformation.setRating(eventRating);
        eventCreationInformation.setBasePrice(new Price(Currency.USD, eventPrice));
        eventCreationInformation.setName(eventName);

        EventService eventService = context.getBean(EventService.class);

        return eventService.create(eventCreationInformation);
    }

    public ShowEvent registerShowEvent(String eventName, String auditoriumName, Date showTime) throws Exception {
        ShowEvent showEvent = null;

        EventService eventService = context.getBean(EventService.class);

        Event event = eventService.getByName(eventName);

        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        Auditorium auditorium = auditoriumService.searchAuditoriumByName(auditoriumName);

        try {
            showEvent = eventService.assignAuditoriumAndDate(event, auditorium, showTime);
        }catch (EventAlreadyExistsException ignored){

        }

        return showEvent;
    }

    public UserTicket registerTicketForFirstAvailableTime(User user, String eventName) throws Exception {
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

        return bookingService.bookTicket(user, price, ticketCreationInformation);
    }

    private User registerUser(String userName, String userEmail) {
        UserFacade userFacade = context.getBean(UserFacade.class);

        UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
        userRegistrationInformation.setUserName(userName);
        userRegistrationInformation.setUserEmail(userEmail);

        return userFacade.register(userRegistrationInformation);
    }

    private String[] getAuditoriums() {
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        List<Auditorium> auditorium = auditoriumService.getAuditoriums();

        List<String> auditAsString = new ArrayList<>(auditorium.size());
        for (Auditorium auditorium1 : auditorium) {
            auditAsString.add(auditorium1.getName());
        }

        return auditAsString.toArray(new String[auditAsString.size()]);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(final ApplicationContext context) {
        this.context = context;
    }
}
