package spring.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum RatingType {
    HIGH(1.25), MID(1.0), LOW(0.75);

    Double priceIncrement = 1.0;

    RatingType(final Double priceIncrement) {
        this.priceIncrement = priceIncrement;
    }

    public Double getPriceIncrement() {
        return priceIncrement;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("priceIncrement", priceIncrement)
                .toString();
    }
}
