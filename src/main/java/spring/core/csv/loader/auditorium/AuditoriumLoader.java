package spring.core.csv.loader.auditorium;

import spring.core.data.Auditorium;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

public interface AuditoriumLoader {
    List<Auditorium> loadAuditoriumList();


    @Required
    void setResourceName(String resourceName);
}
