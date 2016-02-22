package spring.core.aop.aspect.impl;

import spring.core.aop.AOPHelper;
import spring.core.aop.annotation.Countable;
import spring.core.aop.annotation.Loggable;
import spring.core.aop.aspect.CounterAspect;
import spring.core.aop.aspect.LoggerAspect;
import spring.core.aop.handler.CountableMethodHandler;
import spring.core.aop.handler.data.CountedEventMethodData;
import spring.core.data.TicketCreationInformation;
import spring.core.data.User;
import spring.core.data.UserStatistic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Aspect
public class BookingAspect implements CounterAspect, LoggerAspect {
    private static final Logger LOGGER = LogManager.getLogger(BookingAspect.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("execution(* spring.core.service.BookingService.*(..))")
    public void inBookingService() {
    }

    @Pointcut("execution(public * * (.., spring.core.data.TicketCreationInformation, ..))")
    public void methodWithTicketCreationInformationParameter() {
    }

    @Before("inBookingService() && @annotation(countable) && methodWithTicketCreationInformationParameter()")
    public void beforeBookingServiceCountableMethod(JoinPoint point, Countable countable) {
        TicketCreationInformation ticketCreationInformation = (TicketCreationInformation) AOPHelper.getArgWithType
                (point, TicketCreationInformation.class);

        String methodName = point.getSignature().getName();

        assert ticketCreationInformation != null;
        String eventName = ticketCreationInformation.getShowEvent().getEvent().getName();

        CountableMethodHandler countableHandler = applicationContext.getBean(countable.handler());
        countableHandler.handle(new CountedEventMethodData(methodName, eventName));
    }

    @After("inBookingService() && @annotation(countable) && @annotation(loggable) && " +
            "methodWithTicketCreationInformationParameter()")
    public void afterBookingServiceCountableMethod(JoinPoint point, Countable countable, Loggable loggable) {
        TicketCreationInformation ticketCreationInformation = (TicketCreationInformation) AOPHelper.getArgWithType
                (point, TicketCreationInformation.class);

        String methodName = point.getSignature().getName();

        assert ticketCreationInformation != null;
        String eventName = ticketCreationInformation.getShowEvent().getEvent().getName();

        CountableMethodHandler countableHandler = applicationContext.getBean(countable.handler());

        Integer count = countableHandler.getCount(new CountedEventMethodData(methodName, eventName));

        LOGGER.info("Event {} for method {} accessed {} times!", ticketCreationInformation.getShowEvent().getEvent()
                .getName(), methodName, count);
    }

    @After("inBookingService() && execution(* *.bookTicket(.., spring.core.data.User, ..))")
    public void countBookedUserTicket(JoinPoint joinPoint) throws Throwable {
        User user = AOPHelper.getArgWithType(joinPoint, User.class);
        assert user != null;

        UserStatistic userStatistic = user.getUserStatistic();
        if (userStatistic == null) {
            userStatistic = new UserStatistic();
            user.setUserStatistic(userStatistic);
        }

        userStatistic.setTicketsNumber(userStatistic.getTicketsNumber() + 1);
    }

}
