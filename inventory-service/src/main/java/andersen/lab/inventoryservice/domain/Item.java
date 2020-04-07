package andersen.lab.inventoryservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private Integer stock;

}
