package andersen.lab.cartservice.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private CategoryDTO category;

}
