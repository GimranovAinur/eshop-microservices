package andersen.lab.productservice.service.impl;

import andersen.lab.productservice.domain.Product;
import andersen.lab.productservice.dto.InventoryItemDTO;
import andersen.lab.productservice.repository.ProductRepository;
import andersen.lab.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private InventoryServiceFeign inventoryServiceFeign;

    public ProductServiceImpl(ProductRepository productRepository, InventoryServiceFeign inventoryServiceFeign) {
        this.productRepository = productRepository;
        this.inventoryServiceFeign = inventoryServiceFeign;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> getAll() {
        Map<Long, Integer> inventory = getInventoryItems();
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(product -> (inventory.get(product.getId()) != null) && (inventory.get(product.getId()) > 0))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    private Map<Long, Integer> getInventoryItems() {
        Map<Long, Integer> inventory = new HashMap<>();
        List<InventoryItemDTO> items = inventoryServiceFeign.getInventory();
        for(InventoryItemDTO item : items) {
            inventory.put(item.getProductId(), item.getStock());
        }
        return inventory;
    }
}
