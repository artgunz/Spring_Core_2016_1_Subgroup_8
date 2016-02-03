package spring.core.data;

import java.util.List;

public class Auditorium {
    String name;
    Integer numberOfSeats;
    List<Integer> vipSeats;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(final Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(final List<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }
}
