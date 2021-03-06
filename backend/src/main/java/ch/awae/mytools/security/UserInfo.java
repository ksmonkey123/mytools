package ch.awae.mytools.security;

import ch.awae.mytools.user.User;
import ch.awae.mytools.user.UserRole;

import javax.annotation.Nonnull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserInfo implements Principal {

    private final User user;

    public UserInfo(@Nonnull User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public long getId() {
        return user.getId();
    }

    public List<String> getRoles() {
        return UserRole.sortByEnumOrder(user.getRoles());
    }

    public boolean isActive() {
        return user.isEnabled();
    }

}
