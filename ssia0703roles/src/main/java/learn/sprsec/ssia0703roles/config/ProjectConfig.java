package learn.sprsec.ssia0703roles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("jack")
                .password("12345")
//                .authorities("ROLE_ADMIN")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("jane")
                .password("12345")
//                .authorities("ROLE_MANAGER")
                .roles("MANAGER")
                .build();
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        return  userDetailsManager;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest()
//                .hasRole("ADMIN");
                .access("hasRole('ADMIN')");
//                .denyAll();   // may be used for restricting access to a special group of requests
    }
}
