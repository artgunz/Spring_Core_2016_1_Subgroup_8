package spring.core.service.impl;

import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.service.AuditoriumService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    AuditoriumDAO auditoriumDAO;

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumDAO.getAuditoriums();
    }

    
}
