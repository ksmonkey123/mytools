package ch.awae.mytools.user;

import ch.awae.mytools.security.AuthInfo;
import ch.awae.mytools.security.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/rest/user")
@Transactional
public class UserController {

    private final UserRepository repo;
    private final UserService service;

    public UserController(UserRepository repo, UserService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping("/me")
    public UserInfo getCurrentUser() {
        return AuthInfo.getUserInfo();
    }

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        User user = repo.findById(AuthInfo.getUserInfo().getId())
                .filter(u -> service.validatePassword(u, request.password))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad credentials"));
        service.setPassword(user, request.newPassword);
    }

    static class PasswordChangeRequest {
        public @NotEmpty String password;
        public @NotEmpty @Size(min = 8) String newPassword;
    }

}
