package ch.awae.mytools.user;

import ch.awae.mytools.security.AuthInfo;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Nonnull
    @Override
    public User getCurrentUser() {
        return repository.findById(AuthInfo.getUserInfo().getId())
                .orElseThrow(NoSuchElementException::new);
    }

    @Nonnull
    @Override
    public User changePassword(@Nonnull User user, @Nullable String oldPassword, @Nonnull String newPassword) {
        if (oldPassword != null && !encoder.matches(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad credentials");
        }
        user.setPassword(encoder.encode(newPassword));
        return user;
    }

    @Nonnull
    @Override
    public User createUser(@Nonnull String username, @Nonnull String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        return repository.save(user);
    }

    @Nonnull
    @Override
    public List<User> getAllUsers() {
        return repository.findAll(Sort.by("username"));
    }

    @Nonnull
    @Override
    public User addRole(@Nonnull User user, @Nonnull String role) {
        if ("admin".equals(user.getUsername())) {
            // cannot change roles of default admin
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot change roles of default admin!");
        }
        user.getRoles().add(role);
        return user;
    }

    @Nonnull
    @Override
    public User removeRole(@Nonnull User user, @Nonnull String role) {
        if ("admin".equals(user.getUsername())) {
            // cannot change roles of default admin
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot change roles of default admin!");
        }
        if (UserRole.ADMIN.getValue().equals(role)) {
            // cannot remove admin from yourself
            if (AuthInfo.getUserInfo().getId() == user.getId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot de-admin yourself!");
            }
        }
        user.getRoles().remove(role);
        return user;
    }

    @Nonnull
    @Override
    public User setUserEnabledFlag(@Nonnull User user, boolean active) {
        if (!active && AuthInfo.getUserInfo().getId() == user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot disable yourself!");
        }
        user.setEnabled(active);
        return user;
    }
}
