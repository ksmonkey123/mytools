package ch.awae.mytools.user;

import javax.annotation.Nonnull;
import java.util.Objects;

public enum UserRole {

    ADMIN("ROLE_ADMIN");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Nonnull
    public static UserRole byValue(String value) {
        for (UserRole role : UserRole.values()) {
            if (Objects.equals(role.value, value)) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }

}
