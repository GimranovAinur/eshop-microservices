package andersen.lab.productservice.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {

    private Long productId;

    private Integer stock = 0;

}
