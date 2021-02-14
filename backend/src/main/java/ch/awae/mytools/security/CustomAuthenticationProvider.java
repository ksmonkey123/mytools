package ch.awae.mytools.security;

import ch.awae.mytools.user.User;
import ch.awae.mytools.user.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final UserRepository repo;

    public CustomAuthenticationProvider(PasswordEncoder encoder, UserRepository repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    private static Authentication buildAuthHolder(User user) {
        List<SimpleGrantedAuthority> roles = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(new UserInfo(user), null, roles);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return repo.findByUsername(authentication.getName())
                .filter(user -> encoder.matches(authentication.getCredentials().toString(), user.getPassword()))
                .map(CustomAuthenticationProvider::buildAuthHolder)
                .orElse(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
