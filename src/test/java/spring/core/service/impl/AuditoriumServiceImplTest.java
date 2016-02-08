package spring.core.service.impl;

import spring.core.configuration.SpringConfiguration;
import spring.core.exception.ElementNotFoundException;
import spring.core.service.AuditoriumService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class AuditoriumServiceImplTest {

    @Autowired
    AuditoriumService auditoriumService;

    @Test
    public void test1() throws Exception {
        assertEquals("class spring.core.service.impl.AuditoriumServiceImpl",
                this.auditoriumService.getClass().toString());
    }

    @Test
    public void test2() throws Exception {
        assertNotNull(auditoriumService.getAuditoriums());
    }

    @Test
    public void test3() throws Exception {
        assertNotNull(auditoriumService.getVipSeats("Red"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void test4() throws Exception {
        auditoriumService.getVipSeats("Orange");
    }

    @Test
    public void test5() throws Exception {
        assertNotNull(auditoriumService.getSeatsNumber("Red"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void test6() throws Exception {
        auditoriumService.getSeatsNumber("Orange");
    }
}