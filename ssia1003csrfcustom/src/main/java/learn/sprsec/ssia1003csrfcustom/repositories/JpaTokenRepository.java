package learn.sprsec.ssia1003csrfcustom.repositories;

import learn.sprsec.ssia1003csrfcustom.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByIdentifier(String identifier);
}
