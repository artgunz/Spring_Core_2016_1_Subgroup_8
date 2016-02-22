package spring.core.dao.impl.mybatis;

import spring.core.dao.AuditoriumDAO;
import spring.core.dao.EventDAO;
import spring.core.dao.impl.mybatis.mapper.EventMapper;
import spring.core.dao.impl.mybatis.utils.MapperUtility;
import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.EventCreationInformation;
import spring.core.data.Price;
import spring.core.data.vo.EventVO;
import spring.core.data.vo.PriceVO;
import spring.core.data.Rating;
import spring.core.data.ShowEvent;
import spring.core.data.vo.RatingVO;
import spring.core.data.vo.ShowEventVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("myBatisEventDAOImpl")
@Profile("dev")
public class MyBatisEventDAOImpl implements EventDAO {

    @Autowired
    MapperUtility mapperUtility;

    EventMapper eventMapper;

    @Autowired
    AuditoriumDAO auditoriumDAO;

    @Override
    public Event createEvent(final EventCreationInformation eventCreationInformation) throws Exception {
        Long ratingPk;
        Rating rating = getMapper().selectRatingByName(eventCreationInformation.getRating().getName());
        if (rating == null) {
            RatingVO ratingVO = new RatingVO();
            ratingVO.setName(eventCreationInformation.getRating().getName());
            ratingVO.setPriceIncrement(eventCreationInformation.getRating().getPriceIncrement());
            getMapper().insertRating(ratingVO);
            ratingPk = ratingVO.getPk();
        } else {
            ratingPk = rating.getPk();
        }

        Long pricePk;
        Price price = getMapper().selectPrice(eventCreationInformation.getBasePrice().getCurrency().name(),
                eventCreationInformation.getBasePrice().getValue());
        if (price == null) {
            PriceVO priceVO = new PriceVO();
            priceVO.setCurrency(eventCreationInformation.getBasePrice().getCurrency().name());
            priceVO.setValue(eventCreationInformation.getBasePrice().getValue());
            getMapper().insertPrice(priceVO);
            pricePk = priceVO.getPk();
        } else {
            pricePk = price.getPk();
        }

        EventVO eventVO = new EventVO();
        eventVO.setName(eventCreationInformation.getName());
        eventVO.setBasePricePk(pricePk);
        eventVO.setRatingPk(ratingPk);
        getMapper().insertEvent(eventVO);

        return getMapper().selectEventByName(eventCreationInformation.getName());
    }

    @Override
    public void deleteEvent(final Event event) {

    }

    @Override
    public Event searchEventByName(final String eventName) throws Exception {
        return getMapper().selectEventByName(eventName);
    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        return getMapper().selectAllEvents();
    }

    @Override
    public List<ShowEvent> getAllShows() throws Exception {
        List<ShowEventVO> list = getMapper().selectAllShows();
        List<ShowEvent> result = new ArrayList<>();

        for(ShowEventVO showEventVO : list){
            Event event = getMapper().selectEvent(showEventVO.getEventPk());
            Auditorium auditorium = auditoriumDAO.searchAuditoriumByName(showEventVO.getAuditorium());
            Date date = showEventVO.getDate();

            ShowEvent showEvent = new ShowEvent(event, auditorium, date);
            result.add(showEvent);
        }

        return result;
    }

    @Override
    public void addShowEvent(final ShowEvent showEvent) throws Exception {
        Event event = getMapper().selectEventByName(showEvent.getEvent().getName());

        ShowEventVO showEventVO = new ShowEventVO();
        showEventVO.setAuditorium(showEvent.getAuditorium().getName());
        showEventVO.setDate(showEvent.getShowTime());
        showEventVO.setEventPk(event.getPk());

        getMapper().insertShowEvent(showEventVO);
    }

    private EventMapper getMapper() throws Exception {
        if (this.eventMapper == null) {
            this.eventMapper = mapperUtility.getMapperForType(EventMapper.class);
        }

        return this.eventMapper;
    }
}
