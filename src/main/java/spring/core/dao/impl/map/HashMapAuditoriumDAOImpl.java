package spring.core.dao.impl.map;

import spring.core.csv.loader.auditorium.AuditoriumLoader;
import spring.core.dao.AuditoriumDAO;
import spring.core.data.Auditorium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Profile("test")
public class HashMapAuditoriumDAOImpl implements AuditoriumDAO {
    private static final Logger LOGGER = LogManager.getLogger(HashMapAuditoriumDAOImpl.class);

    private final static Set<Auditorium> auditoriumStorage = new HashSet<>();

    @Autowired
    AuditoriumLoader auditoriumLoader;

    @Override
    public Auditorium searchAuditoriumByName(final String name) {
        for (final Auditorium auditorium : auditoriumStorage) {
            if (auditorium.getName().equals(name)) {
                LOGGER.debug("Founded auditorium = {}....", auditorium);

                return auditorium;
            }
        }

        return null;
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        final List<Auditorium> mainList = new ArrayList<>();
        mainList.addAll(auditoriumStorage);

        LOGGER.debug("Founded auditoriums = {}....", mainList);

        return mainList;
    }

    @Override
    public Auditorium addAuditorium(final Auditorium auditorium) {
        LOGGER.debug("Adding auditorium = {}....", auditorium);

        auditoriumStorage.add(auditorium);
        return auditorium;
    }

    @PostConstruct
    public void readDB() {
        LOGGER.debug("Loading auditoriums....");

        for (Auditorium auditorium : auditoriumLoader.loadAuditoriumList()) {
            addAuditorium(auditorium);
        }

        LOGGER.info("Loaded {} auditoriums....", auditoriumStorage.size());
    }
}
