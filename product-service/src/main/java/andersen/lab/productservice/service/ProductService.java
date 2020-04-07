package andersen.lab.productservice.service;

import andersen.lab.productservice.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProduct(Product product);

    boolean productExists(Long id);

    List<Product> getAll();

    Optional<Product> getProductById(Long id);

}
