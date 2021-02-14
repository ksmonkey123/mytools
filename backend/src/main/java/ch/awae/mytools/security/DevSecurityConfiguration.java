package ch.awae.mytools.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevSecurityConfiguration extends SecurityConfiguration {

    public DevSecurityConfiguration(CustomAuthenticationProvider authProvider) {
        super(authProvider);
        System.out.println("dev security");
    }

    @Override
    protected void setupAuthMethod(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();
    }
}
