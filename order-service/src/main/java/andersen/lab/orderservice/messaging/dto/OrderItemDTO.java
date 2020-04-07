package andersen.lab.orderservice.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    private Long productId;

    private Integer amount;

}
