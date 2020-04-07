package andersen.lab.productservice.service.impl;

import andersen.lab.productservice.dto.InventoryItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "inventory-service")
@Service
public interface InventoryServiceFeign {

    @GetMapping("/api/inventory")
    List<InventoryItemDTO> getInventory();

    @GetMapping("/api/inventory/{productId}")
    InventoryItemDTO getProductStock(@PathVariable Long productId);
}
