package andersen.lab.orderservice.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date createTime;

    @NumberFormat(pattern = "0.00")
    private Double totalPrice;

}
