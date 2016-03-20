package spring.core.data;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserRegistrationInformation {
    String userName;
    String userEmail;
    Date birthDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("userEmail", userEmail)
                .append("birthDate", birthDate)
                .toString();
    }
}
