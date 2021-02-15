package ch.awae.mytools.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InitialUserCreationRunner implements CommandLineRunner {

    private final UserService service;
    private final UserRepository repo;

    @Autowired
    public InitialUserCreationRunner(UserService service, UserRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        Optional<User> admin = repo.findByUsername("admin");
        if (!admin.isPresent()) {
            User user = new User();
            user.setUsername("admin");
            service.changePassword(user, null, "password");
            user.getRoles().add("ROLE_ADMIN");
            repo.saveAndFlush(user);
        }
    }
}
