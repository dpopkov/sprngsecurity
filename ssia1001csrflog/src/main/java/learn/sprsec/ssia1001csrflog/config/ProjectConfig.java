package learn.sprsec.ssia1001csrflog.config;

import learn.sprsec.ssia1001csrflog.filters.CsrfTokenLoggingFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(
                    new CsrfTokenLoggingFilter(), CsrfFilter.class)
                .authorizeRequests()
                    .anyRequest().permitAll();
    }
}
