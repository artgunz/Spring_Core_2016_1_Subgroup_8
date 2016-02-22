package spring.core.data;

public class Discount {
    Double value;

    public Discount(final Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }
}
