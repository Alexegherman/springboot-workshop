package eu.accesa.training.config;

import eu.accesa.training.auth.AuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new AuthorizationFilter());
        reg.setName("authFilter");
        reg.addUrlPatterns("/price/*");  //path definition
        reg.setOrder(1);
        return reg;
    }
}
