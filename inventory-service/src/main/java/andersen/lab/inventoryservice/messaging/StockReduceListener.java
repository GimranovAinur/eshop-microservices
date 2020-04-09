package andersen.lab.inventoryservice.messaging;

import andersen.lab.inventoryservice.config.RabbitMQConfig;
import andersen.lab.inventoryservice.domain.Item;
import andersen.lab.inventoryservice.messaging.dto.OrderItemDTO;
import andersen.lab.inventoryservice.service.InventoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockReduceListener {

    private InventoryService inventoryService;

    @Autowired
    public StockReduceListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RabbitListener(queues= RabbitMQConfig.QUEUE_NAME)
    public void reduceStock(OrderItemDTO orderItemDTO) {
        Optional<Item> item = inventoryService.getByProductId(orderItemDTO.getProductId());
        item.ifPresent(inventoryItem -> {
            inventoryItem.setStock(inventoryItem.getStock() - orderItemDTO.getAmount());
            inventoryService.save(inventoryItem);
            System.out.println("Количество уменьшено на " + orderItemDTO.getAmount());
        });
    }

}
