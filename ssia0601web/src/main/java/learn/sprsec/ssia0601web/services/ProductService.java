package learn.sprsec.ssia0601web.services;

import learn.sprsec.ssia0601web.entities.Product;
import learn.sprsec.ssia0601web.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
