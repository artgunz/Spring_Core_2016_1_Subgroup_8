package spring.core.aop.handler.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CountedMethodData {
    private String methodName;

    public CountedMethodData(final String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof CountedMethodData)) return false;

        final CountedMethodData that = (CountedMethodData) o;

        return new EqualsBuilder()
                .append(methodName, that.methodName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(methodName)
                .toHashCode();
    }
}
