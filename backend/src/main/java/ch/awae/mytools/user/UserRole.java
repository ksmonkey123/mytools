package ch.awae.mytools.user;

import javax.annotation.Nonnull;
import java.util.*;

public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    CNC_POST_PROCESSOR("ROLE_CNCPP");

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

    public static List<String> sortByEnumOrder(@Nonnull Collection<? extends String> roles) {
        List<String> result = new ArrayList<>();
        for (UserRole role : values()) {
            if (roles.contains(role.value)) {
                result.add(role.value);
            }
        }
        return result;
    }

}
