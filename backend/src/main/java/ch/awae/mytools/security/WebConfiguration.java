package ch.awae.mytools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    private final AuthorityRefreshingInterceptor authorityRefreshingInterceptor;

    @Autowired
    public WebConfiguration(AuthorityRefreshingInterceptor authorityRefreshingInterceptor) {
        this.authorityRefreshingInterceptor = authorityRefreshingInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityRefreshingInterceptor).addPathPatterns("/**");
    }
}
