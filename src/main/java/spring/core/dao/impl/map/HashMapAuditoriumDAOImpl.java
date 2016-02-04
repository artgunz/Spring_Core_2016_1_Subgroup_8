package spring.core.dao.impl.map;

import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;
import spring.core.data.Event;
import spring.core.data.ShowEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository("hashMapAuditoriumDAO")
public class HashMapAuditoriumDAOImpl implements AuditoriumDAO {
    private final static Set<Auditorium> auditoriumStorage = new HashSet<>();

    @Override
    public Auditorium searchAuditoriumByName(final String name) {
        for (final Auditorium auditorium : auditoriumStorage) {
            if (auditorium.getName().equals(name)) {
                return  auditorium;
            }
        }

        return null;
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        final List<Auditorium> mainList = new ArrayList<>();
        mainList.addAll(auditoriumStorage);
        return mainList;
    }

    @Override
    public Auditorium addAuditorium(final Auditorium auditorium) {
        auditoriumStorage.add(auditorium);
        return auditorium;
    }
}
