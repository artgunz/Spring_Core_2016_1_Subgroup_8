package spring.core.data;

public enum Rating {
    HIGH(1.25), MID(1.0), LOW(0.75);

    Double priceIncrement = 1.0;

    Rating(final Double priceIncrement) {
        this.priceIncrement = priceIncrement;
    }
}
