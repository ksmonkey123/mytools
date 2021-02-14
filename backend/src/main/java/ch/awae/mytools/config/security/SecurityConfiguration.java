package ch.awae.mytools.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public abstract class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authProvider;

    @Autowired
    public SecurityConfiguration(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID");
        setupAuthMethod(http);
    }

    protected abstract void setupAuthMethod(HttpSecurity http) throws Exception;
}
