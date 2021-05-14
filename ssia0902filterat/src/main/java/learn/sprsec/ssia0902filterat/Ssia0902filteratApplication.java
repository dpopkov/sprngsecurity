package learn.sprsec.ssia0902filterat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class Ssia0902filteratApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ssia0902filteratApplication.class, args);
    }

}
