package andersen.lab.orderservice.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private ProductDTO product;

    private Integer amount;

}
