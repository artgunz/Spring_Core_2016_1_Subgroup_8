package spring.core.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Rating extends Item {
    String name;
    Double priceIncrement = 1.0;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("priceIncrement", priceIncrement)
                .toString();
    }
}
