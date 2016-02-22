package spring.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Price extends Item {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("currency", currency)
                .append("value", value)
                .toString();
    }
}
