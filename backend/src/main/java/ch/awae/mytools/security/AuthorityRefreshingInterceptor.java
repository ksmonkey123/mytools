package ch.awae.mytools.security;

import ch.awae.mytools.user.User;
import ch.awae.mytools.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@Component
public class AuthorityRefreshingInterceptor implements HandlerInterceptor {

    private final UserRepository repository;
    private final CustomAuthenticationProvider provider;

    @Autowired
    public AuthorityRefreshingInterceptor(UserRepository repository, CustomAuthenticationProvider provider) {
        this.repository = repository;
        this.provider = provider;
    }

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserInfo) {
            refreshRoles((UserInfo) principal);
        }
        return true;
    }

    private void refreshRoles(UserInfo principal) {
        User user = repository
                .findById(principal.getId())
                .filter(User::isEnabled)
                .orElseThrow(NoSuchElementException::new);
        Authentication authentication = provider.buildAuthHolder(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
