package spring.core.dao.impl.mybatis.mapper;

import spring.core.data.Currency;
import spring.core.data.Event;
import spring.core.data.Price;
import spring.core.data.ShowEvent;
import spring.core.data.vo.EventVO;
import spring.core.data.vo.PriceVO;
import spring.core.data.Rating;
import spring.core.data.vo.RatingVO;
import spring.core.data.vo.ShowEventVO;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EventMapper {
    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "price", javaType = Price.class, select = "selectPrice"),
            @Arg(column = "rating", javaType = Rating.class, select = "selectRating")
    })
    @Select("SELECT * from event WHERE name = #{eventName}")
    Event selectEventByName(String eventName);

    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "price", javaType = Price.class, select = "selectPrice"),
            @Arg(column = "rating", javaType = Rating.class, select = "selectRating")
    })
    @Select("SELECT * from event WHERE pk = #{eventPk}")
    Event selectEvent(Long eventPk);

    @Results({
            @Result(property = "pk", column = "pk"),
            @Result(property = "auditorium", column = "auditorium"),
            @Result(property = "eventPk", column = "event"),
            @Result(property = "date", column = "date")
    })
    @Select("SELECT * from event_shows")
    List<ShowEventVO> selectAllShows();

    @ConstructorArgs({
            @Arg(column = "name", javaType = String.class),
            @Arg(column = "price", javaType = Price.class, select = "selectPrice"),
            @Arg(column = "rating", javaType = Rating.class, select = "selectRating")
    })
    @Select("SELECT * from event")
    List<Event> selectAllEvents();

    @Select("SELECT * FROM rating WHERE pk = #{ratingPk}")
    @Results({
            @Result(property = "pk", column = "pk"),
            @Result(property = "name", column = "name"),
            @Result(property = "priceIncrement", column = "price_increment")
    })
    Rating selectRating(Long ratingPk);

    @Select("SELECT pk, UPPER(name), price_increment FROM rating WHERE name = #{ratingName}")
    @Results({
            @Result(property = "pk", column = "pk"),
            @Result(property = "name", column = "name"),
            @Result(property = "priceIncrement", column = "price_increment")
    })
    Rating selectRatingByName(String ratingName);

    @Select(value = "SELECT * FROM price WHERE currency = #{currency} AND value = #{value}")
    Price selectPrice(@Param("currency")String currency,@Param("value")Double value);

    @Insert("INSERT into price(currency,value) VALUES(#{currency}, #{value})")
    @Options(useGeneratedKeys = true, keyProperty = "pk", keyColumn = "pk")
    void insertPrice(PriceVO priceVO);

    @Insert("INSERT into rating(name,price_increment) VALUES(#{name}, #{priceIncrement})")
    @Options(useGeneratedKeys = true, keyProperty = "pk", keyColumn = "pk")
    void insertRating(RatingVO ratingVO);

    @Insert("INSERT into event(name,price,rating) VALUES(#{name}, #{basePricePk}, #{ratingPk})")
    @Options(useGeneratedKeys = true, keyProperty = "pk",keyColumn = "pk")
    void insertEvent(EventVO eventVO);

    @Insert("INSERT into event_shows(event,auditorium,date) VALUES(#{eventPk}, #{auditorium}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "pk",keyColumn = "pk")
    void insertShowEvent(ShowEventVO showEventVO);
}
