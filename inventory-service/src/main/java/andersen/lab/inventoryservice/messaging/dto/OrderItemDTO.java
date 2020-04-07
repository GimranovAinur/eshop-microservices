package andersen.lab.inventoryservice.messaging.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long productId;

    private Integer amount;

}
