package spring.core;

import spring.core.configuration.SpringConfiguration;
import spring.core.data.UserRegistrationInformation;
import spring.core.facade.UserFacade;
import spring.core.facade.impl.UserFacadeImpl;
import spring.core.service.AuditoriumService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        UserFacade userFacade = context.getBean(UserFacadeImpl.class);

        UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
        userRegistrationInformation.setUserName("User");
        userRegistrationInformation.setUserEmail("email@email.ua");

        userFacade.register(userRegistrationInformation);

        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        auditoriumService.getAuditoriums();
        auditoriumService.getVipSeats("Red");
    }
}
