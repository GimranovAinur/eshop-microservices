package andersen.lab.orderservice.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private CategoryDTO category;

}
