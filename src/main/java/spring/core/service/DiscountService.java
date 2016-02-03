package spring.core.service;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface DiscountService {
    Discount getDiscount(User user, Event event, Date date);
}
