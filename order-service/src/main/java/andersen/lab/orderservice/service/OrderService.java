package andersen.lab.orderservice.service;

import andersen.lab.orderservice.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> getUserOrders(Long userId);

    void saveOrder(Order order);

}
