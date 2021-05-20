package learn.sprsec.ssia1003csrfcustom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final CsrfTokenRepository csrfTokenRepository;

    public ProjectConfig(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final Customizer<CsrfConfigurer<HttpSecurity>> csrfConfigurerCustomizer = c -> {
            c.csrfTokenRepository(csrfTokenRepository);

//            c.ignoringAntMatchers("/ciao/42");

            String pattern = "/ciao/[1-9]{2}";
            String httpMethod = HttpMethod.POST.name();
            RegexRequestMatcher matcher = new RegexRequestMatcher(pattern, httpMethod);
            c.ignoringRequestMatchers(matcher);
        };
        http.csrf(csrfConfigurerCustomizer);
        http.authorizeRequests()
                .anyRequest().permitAll();
    }
}
