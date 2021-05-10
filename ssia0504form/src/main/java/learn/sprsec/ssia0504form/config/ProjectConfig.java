package learn.sprsec.ssia0504form.config;

import learn.sprsec.ssia0504form.handlers.CustomAuthenticationFailureHandler;
import learn.sprsec.ssia0504form.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public ProjectConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler,
                         CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .and()
                .httpBasic();
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
