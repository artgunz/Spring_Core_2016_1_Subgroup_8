package spring.core.strategy.discount;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface DiscountStrategy {
    Discount calculateDiscount(final User user, final Event event, final Date date);
}
