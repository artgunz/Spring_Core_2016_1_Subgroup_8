package spring.core.data.vo;

public class RatingVO {
    Long pk;
    String name;
    Double priceIncrement;

    public Long getPk() {
        return pk;
    }

    public void setPk(final Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPriceIncrement() {
        return priceIncrement;
    }

    public void setPriceIncrement(final Double priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
