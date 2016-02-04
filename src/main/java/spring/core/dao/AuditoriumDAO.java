package spring.core.dao;

import spring.core.data.Auditorium;

import java.util.List;

public interface AuditoriumDAO {

    Auditorium searchAuditoriumByName(String name);
    List<Auditorium> getAuditoriums();
    Auditorium addAuditorium(Auditorium auditorium);
}
