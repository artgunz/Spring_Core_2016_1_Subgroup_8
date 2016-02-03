package spring.core.service;

import spring.core.data.Auditorium;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AuditoriumService {

    List<Auditorium> getAuditoriums();

    Integer getSeatsNumber(String auditoriumName); //TODO do we really need it???

    Integer getVipSeats(String auditoriumName); //TODO do we really need it???
}
