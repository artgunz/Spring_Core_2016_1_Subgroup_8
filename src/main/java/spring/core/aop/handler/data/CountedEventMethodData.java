package spring.core.aop.handler.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CountedEventMethodData extends CountedMethodData {
    private String eventName;

    public CountedEventMethodData(final String methodName, final String eventName) {
        super(methodName);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(final String eventName) {
        this.eventName = eventName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof CountedEventMethodData)) return false;

        final CountedEventMethodData that = (CountedEventMethodData) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(eventName, that.eventName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(eventName)
                .toHashCode();
    }
}
