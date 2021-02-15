package ch.awae.mytools.user;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface UserService {

    @Nonnull User getCurrentUser();

    void changePassword(@Nonnull User user, @Nullable String oldPassword, @Nonnull String newPassword);

    @Nonnull
    User createUser(@Nonnull String username, @Nonnull String password);

    List<User> getAllUsers();

    @Nonnull
    User addRole(@Nonnull User user, @Nonnull String role);

    @Nonnull
    User removeRole(@Nonnull User user, @Nonnull String role);
}
