package ch.awae.mytools.user;

import ch.awae.mytools.security.AuthInfo;
import ch.awae.mytools.security.UserInfo;
import ch.awae.mytools.validation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/rest/user")
@Transactional
public class UserController {

    private final UserService service;
    private final UserRepository repo;

    @Autowired
    public UserController(UserService service, UserRepository repo) {
        this.service = service;
        this.repo = repo;
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
        @NotEmpty
        public String password;
        @NotEmpty
        @Size(min = 8)
        public String newPassword;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public UserInfo createUser(@Valid @RequestBody UserCreationRequest request) {
        return new UserInfo(service.createUser(request.username, request.password));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/list")
    public List<UserInfo> getUserList() {
        List<User> allUsers = service.getAllUsers();
        return allUsers.stream().map(UserInfo::new).collect(Collectors.toList());
    }

    static class UserCreationRequest {
        @NotEmpty
        @Size(min = 5, max = 20)
        public String username;
        @NotEmpty
        @Size(min = 8)
        public String password;
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("/{userId}/role")
    public UserInfo patchUserRoles(@PathVariable long userId, @Valid @RequestBody RolePatchRequest request) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        if (request.add) {
            return new UserInfo(service.addRole(user, request.role));
        } else {
            return new UserInfo(service.removeRole(user, request.role));
        }
    }

    static class RolePatchRequest {
        public boolean add;
        @NotEmpty
        @Role
        public String role;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeUserPassword(@PathVariable long userId, @Valid @RequestBody AdminPasswordChangeRequest request) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        service.changePassword(user, null, request.password);
    }

    static class AdminPasswordChangeRequest {
        @NotEmpty
        @Size(min = 8)
        public String password;
    }

}
