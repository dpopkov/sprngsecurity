package learn.sprsec.ssia0601web.repositories;

import learn.sprsec.ssia0601web.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
