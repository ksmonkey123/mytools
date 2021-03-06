package ch.awae.mytools.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Profile("!dev")
public class ProdSecurityConfiguration extends SecurityConfigurationBase {

    @Autowired
    public ProdSecurityConfiguration(CustomAuthenticationProvider authProvider) {
        super(authProvider);
        System.out.println("prod security");
    }

    @Override
    protected void setupAuthMethod(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin();
    }
}
