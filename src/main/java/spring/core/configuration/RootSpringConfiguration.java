package spring.core.configuration;

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
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = {"spring.core.*"})
@Import(value = {AspectSpringConfiguration.class, DBSpringConfiguration.class})
public class RootSpringConfiguration {

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("dev")
    public AuditoriumLoader auditoriumLoaderDev() {
        AuditoriumLoader auditoriumLoader = new AuditoriumLoaderImpl();
        auditoriumLoader.setResourceName("classpath:auditorium.csv");

        return auditoriumLoader;
    }

    @Bean(name = "auditoriumLoaderImpl")
    @Profile("test")
    public AuditoriumLoader auditoriumLoaderTest() {
        AuditoriumLoader auditoriumLoader = new AuditoriumLoaderImpl();
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
}
