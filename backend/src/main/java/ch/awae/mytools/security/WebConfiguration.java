package ch.awae.mytools.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final AuthorityRefreshingInterceptor authorityRefreshingInterceptor;

    @Autowired
    public WebConfiguration(AuthorityRefreshingInterceptor authorityRefreshingInterceptor) {
        this.authorityRefreshingInterceptor = authorityRefreshingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityRefreshingInterceptor).addPathPatterns("/rest/**");
    }
}
