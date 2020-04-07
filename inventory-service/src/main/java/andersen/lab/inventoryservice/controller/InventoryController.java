package andersen.lab.inventoryservice.controller;

import andersen.lab.inventoryservice.domain.Item;
import andersen.lab.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("")
    public List<Item> getAllProductInventory() {
        return inventoryService.getAllProductsStock();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Item> findInventoryByProductId(@PathVariable Long productId) {
        Optional<Item> item = inventoryService.getByProductId(productId);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/sell/{productId}/{units}")
    public ResponseEntity<Item> sellByProductCode(@PathVariable("productId") Long productId,
                                                  @PathVariable("units") Integer units) {
        Optional<Item> inventoryItem = inventoryService.getByProductId(productId);
        if(inventoryItem.isPresent()) {
            Item item = inventoryItem.get();
            item.setStock(item.getStock() - units);
            inventoryService.save(item);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/add/{productId}/{units}")
    public ResponseEntity<Item> addToStockByProductCode(@PathVariable("productId") Long productId,
                                                        @PathVariable("units") Integer units) {
        Optional<Item> inventoryItem = inventoryService.getByProductId(productId);
        if(inventoryItem.isPresent()) {
            Item item = inventoryItem.get();
            item.setStock(item.getStock() + units);
            inventoryService.save(item);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
