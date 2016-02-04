package spring.core.data;

public class Price {
    final Currency currency;
    final Double value;

    public Price(final Currency currency, final Double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getValue() {
        return value;
    }
}
