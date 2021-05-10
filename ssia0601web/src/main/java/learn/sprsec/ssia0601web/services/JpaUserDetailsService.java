package learn.sprsec.ssia0601web.services;

import learn.sprsec.ssia0601web.entities.User;
import learn.sprsec.ssia0601web.model.CustomUserDetails;
import learn.sprsec.ssia0601web.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> exceptionSupplier = () -> new UsernameNotFoundException("Not found");
        User user = userRepository.findUserByUsername(username).orElseThrow(exceptionSupplier);
        return new CustomUserDetails(user);
    }
}
