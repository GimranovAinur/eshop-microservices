package andersen.lab.cartservice.model;

import andersen.lab.cartservice.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {

    private ProductDTO product;

    private int amount;

}
