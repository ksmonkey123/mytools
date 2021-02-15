package ch.awae.mytools.user;

import ch.awae.mytools.security.AuthInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    @Override
    public void changePassword(@Nonnull User user, @Nullable String oldPassword, @Nonnull String newPassword) {
        if (oldPassword != null && !encoder.matches(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad credentials");
        }
        user.setPassword(encoder.encode(newPassword));
    }

}
