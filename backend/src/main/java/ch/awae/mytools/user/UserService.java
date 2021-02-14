package ch.awae.mytools.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public boolean validatePassword(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }

    public void setPassword(User user, String password) {
        user.setPassword(encoder.encode(password));
    }

}
