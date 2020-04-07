package andersen.lab.inventoryservice.service;

import andersen.lab.inventoryservice.domain.Item;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Optional<Item> getByProductId(Long id);

    Item save(Item item);

    List<Item> getAllProductsStock();

}
