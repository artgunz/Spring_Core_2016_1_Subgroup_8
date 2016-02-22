package spring.core.data.db;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item {
    Long pk;

    public Long getPk() {
        return pk;
    }

    public void setPk(final Long pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pk", pk)
                .toString();
    }
}
