package spring.core.data;

public class EventCreationInformation {
    String name;
    Price basePrice;
    Rating rating;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Price getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final Price basePrice) {
        this.basePrice = basePrice;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(final Rating rating) {
        this.rating = rating;
    }
}
