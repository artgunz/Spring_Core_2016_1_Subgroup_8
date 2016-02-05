package spring.core.service.impl;

import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.service.AuditoriumService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    AuditoriumDAO auditoriumDAO;

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumDAO.getAuditoriums();
    }

    @Override
    public Integer getSeatsNumber(final String auditoriumName) {
        for (Auditorium auditorium: getAuditoriums()
                ) {
            if(auditorium.getName().equals(auditoriumName)){
                return auditorium.getSeatsCount();
            }
        }
        return 0;
    }

    @Override
    public List<Integer> getVipSeats(final String auditoriumName) {
        for (Auditorium auditorium: getAuditoriums()
             ) {
            if(auditorium.getName().equals(auditoriumName)){
                return auditorium.getVipSeats();
            }
        }
        return null;
    }


}
