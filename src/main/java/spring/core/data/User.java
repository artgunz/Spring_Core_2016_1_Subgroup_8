package spring.core.data;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {
    Long id;
    String name;
    String email;
    Date birthDate;
    UserStatistic userStatistic;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(final UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(name, user.name)
                .append(email, user.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(email)
                .toHashCode();
    }
}
