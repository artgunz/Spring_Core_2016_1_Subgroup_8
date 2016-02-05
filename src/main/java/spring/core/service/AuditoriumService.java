package spring.core.service;

import spring.core.data.Auditorium;

import java.util.List;

public interface AuditoriumService {

    List<Auditorium> getAuditoriums();

    Integer getSeatsNumber(String auditoriumName); //TODO do we really need it???

    List<Integer> getVipSeats(String auditoriumName); //TODO do we really need it???
}
