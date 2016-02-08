package spring.core.service.impl;

import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.exception.ElementNotFoundException;
import spring.core.service.AuditoriumService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private static final Logger LOGGER = LogManager.getLogger(AuditoriumServiceImpl.class);

    @Autowired
    AuditoriumDAO auditoriumDAO;

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumDAO.getAuditoriums();
    }

    @Override
    public Integer getSeatsNumber(final String auditoriumName) throws ElementNotFoundException{
        for (Auditorium auditorium: getAuditoriums()
                ) {
            if(auditorium.getName().equals(auditoriumName)){
                Integer count = auditorium.getSeatsCount();

                LOGGER.debug("Auditorium {} has {} seats",auditoriumName, count);

                return count;
            }
        }

        throw new ElementNotFoundException(String.format("Auditorium '%s' not found", auditoriumName));
    }

    @Override
    public List<Integer> getVipSeats(final String auditoriumName) throws ElementNotFoundException {
        for (Auditorium auditorium: getAuditoriums()
             ) {
            if(auditorium.getName().equals(auditoriumName)){
                List<Integer> vipSeats = auditorium.getVipSeats();

                LOGGER.debug("Auditorium {} has {} vip seats",auditoriumName, vipSeats);

                return vipSeats;
            }
        }

        throw new ElementNotFoundException(String.format("Auditorium '%s' not found", auditoriumName));
    }

    @Override
    public Auditorium searchAuditoriumByName(final String name) {
        return auditoriumDAO.searchAuditoriumByName(name);
    }
}
