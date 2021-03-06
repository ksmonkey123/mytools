package ch.awae.mytools.user;

import ch.awae.mytools.exception.UserNotFoundException;
import ch.awae.mytools.security.AuthInfo;
import ch.awae.mytools.security.UserInfo;
import ch.awae.mytools.validation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @GetMapping("/{userId}")
    public UserInfo getUserById(@PathVariable long userId) {
        return repo.findById(userId)
                .map(UserInfo::new)
                .orElseThrow(() -> new UserNotFoundException(userId));
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
    @PostMapping("")
    public UserInfo createUser(@Valid @RequestBody UserCreationRequest request) {
        return new UserInfo(service.createUser(request.username, request.password));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/list")
    public List<UserInfo> getUserList() {
        List<User> allUsers = service.getAllUsers();
        return allUsers.stream().map(UserInfo::new).collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/roles")
    public List<String> getAllRoles() {
        List<String> roles = new ArrayList<>();
        for (UserRole role : UserRole.values()) {
            roles.add(role.getValue());
        }
        return roles;
    }

    static class UserCreationRequest {
        @NotEmpty
        @Size(min = 4, max = 20)
        public String username;
        @NotEmpty
        @Size(min = 8)
        public String password;
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("/{userId}/role")
    public UserInfo patchUserRoles(@PathVariable long userId, @Valid @RequestBody RolePatchRequest request) {
        User user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
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
    @PatchMapping("/{userId}")
    public UserInfo patchUser(@PathVariable long userId, @Valid @RequestBody PatchUserRequest request) {
        User user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (request.password != null) {
            user = service.changePassword(user, null, request.password);
        }
        if (request.active != null) {
            user = service.setUserEnabledFlag(user, request.active);
        }
        return new UserInfo(user);
    }

    static class PatchUserRequest {
        @Size(min = 8)
        public String password;
        public Boolean active;
    }

}
