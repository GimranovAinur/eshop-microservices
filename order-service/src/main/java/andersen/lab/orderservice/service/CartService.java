package andersen.lab.orderservice.service;

import andersen.lab.orderservice.domain.OrderItem;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    List<OrderItem> getOrderItemFromCart(HttpSession session);

    void cleanCart(HttpSession session);

}
