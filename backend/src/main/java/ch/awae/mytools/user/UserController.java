package ch.awae.mytools.user;

import ch.awae.mytools.security.AuthInfo;
import ch.awae.mytools.security.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/rest/user")
@Transactional
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public UserInfo getCurrentUser() {
        return AuthInfo.getUserInfo();
    }

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        service.changePassword(service.getCurrentUser(), request.password, request.newPassword);
    }

    static class PasswordChangeRequest {
        public @NotEmpty String password;
        public @NotEmpty @Size(min = 8) String newPassword;
    }

}
