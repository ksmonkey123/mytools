package ch.awae.mytools.user;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserService {

    @Nonnull User getCurrentUser();

    void changePassword(@Nonnull User user, @Nullable String oldPassword, @Nonnull String newPassword);

}
