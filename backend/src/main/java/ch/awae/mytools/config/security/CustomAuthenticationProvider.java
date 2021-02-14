package ch.awae.mytools.config.security;

import ch.awae.mytools.user.UserRespository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final UserRespository repo;

    public CustomAuthenticationProvider(PasswordEncoder encoder, UserRespository repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return repo.findByUsername(authentication.getName())
                .filter(user -> encoder.matches(authentication.getCredentials().toString(), user.getPassword()))
                .map(user -> new UsernamePasswordAuthenticationToken(user.getUsername(), null, new ArrayList<>()))
                .orElse(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
