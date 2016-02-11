package spring.core.configuration;

import spring.core.aop.aspect.impl.BookingAspect;
import spring.core.aop.aspect.impl.DefaultLoggerAspect;
import spring.core.aop.aspect.impl.EventAspect;
import spring.core.aop.aspect.impl.EventUserLuckyAspect;
import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.csv.loader.auditorium.impl.AuditoriumLoaderImpl;
import spring.core.strategy.discount.DiscountStrategy;
import spring.core.strategy.discount.impl.UserBirthDiscountStrategyImpl;
import spring.core.strategy.discount.impl.UserTicketsBoughtDiscountStrategyImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = {"spring.core.*"})
@EnableAspectJAutoProxy
public class SpringConfiguration {

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("dev")
    public AuditoriumLoader auditoriumLoaderDev() {
        AuditoriumLoaderImpl auditoriumLoader = new AuditoriumLoaderImpl();
        auditoriumLoader.setResourceName("classpath:auditorium.csv");

        return auditoriumLoader;
    }

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("test")
    public AuditoriumLoader auditoriumLoaderTest() {
        AuditoriumLoaderImpl auditoriumLoader = new AuditoriumLoaderImpl();
        auditoriumLoader.setResourceName("classpath:auditoriumTest.csv");

        return auditoriumLoader;
    }

    @Bean
    public List<DiscountStrategy> discountStrategyList() {
        List<DiscountStrategy> aList = new ArrayList<>();
        aList.add(new UserBirthDiscountStrategyImpl());
        aList.add(new UserTicketsBoughtDiscountStrategyImpl());

        return aList;
    }

    @Bean
    public EventAspect eventAspect() {
        return new EventAspect();
    }

    @Bean
    public BookingAspect bookingAspect() {
        return new BookingAspect();
    }

    @Bean
    public DefaultLoggerAspect defaultAspect() {
        return new DefaultLoggerAspect();
    }

    @Bean
    public EventUserLuckyAspect eventUserLuckyAspect() {
        return new EventUserLuckyAspect();
    }

}
