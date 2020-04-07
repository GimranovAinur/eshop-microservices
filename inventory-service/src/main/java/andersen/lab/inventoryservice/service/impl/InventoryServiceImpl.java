package andersen.lab.inventoryservice.service.impl;

import andersen.lab.inventoryservice.domain.Item;
import andersen.lab.inventoryservice.repository.InventoryRepository;
import andersen.lab.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Optional<Item> getByProductId(Long id) {
        return inventoryRepository.findByProductId(id);
    }

    @Override
    public Item save(Item item) {
        return inventoryRepository.save(item);
    }

    @Override
    public List<Item> getAllProductsStock() {
        return inventoryRepository.findAll();
    }
}
