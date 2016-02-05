package spring.core.configuration;

import spring.core.strategy.discount.DiscountStrategy;
import spring.core.strategy.discount.impl.UserBirthDiscountStrategyImpl;
import spring.core.strategy.discount.impl.UserTicketsBoughtDiscountStrategyImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "spring.core.*" })
public class SpringConfiguration {

    @Bean
    public List<DiscountStrategy> discountStrategyList() {
        List<DiscountStrategy> aList = new ArrayList<>();
        aList.add(new UserBirthDiscountStrategyImpl());
        aList.add(new UserTicketsBoughtDiscountStrategyImpl());

        return aList;
    }

}
