package spring.core.service.impl;

import spring.core.data.Discount;
import spring.core.data.Event;
import spring.core.data.User;
import spring.core.service.DiscountService;
import spring.core.strategy.discount.DiscountStrategy;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Resource(name = "discountStrategyList")
    List<DiscountStrategy> discountStrategyList; //TODO autowire it

    @Override
    public Discount getDiscount(final User user, final Event event, final Date date) {
        Double discountCalculated = 0.0;

        for(DiscountStrategy discountStrategy: discountStrategyList){
            Discount discount = discountStrategy.calculateDiscount(user,  event, date);
            if(discountCalculated < discount.getValue()){
                discountCalculated = discount.getValue();
            }
        }

        return new Discount(discountCalculated);
    }
}
