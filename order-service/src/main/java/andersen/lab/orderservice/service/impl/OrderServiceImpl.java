package andersen.lab.orderservice.service.impl;

import andersen.lab.orderservice.domain.Order;
import andersen.lab.orderservice.domain.OrderItem;
import andersen.lab.orderservice.messaging.StockMessageSender;
import andersen.lab.orderservice.repository.OrderItemRepository;
import andersen.lab.orderservice.repository.OrderRepository;
import andersen.lab.orderservice.service.OrderService;
import andersen.lab.orderservice.service.ProductServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private ProductServiceFeign productServiceFeign;

    private StockMessageSender stockMessageSender;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            ProductServiceFeign productServiceFeign,
                            StockMessageSender stockMessageSender) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productServiceFeign = productServiceFeign;
        this.stockMessageSender = stockMessageSender;
    }


    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        for(Order order : orders) {
            for(OrderItem orderItem : order.getOrderItems()) {
                orderItem.setProduct(productServiceFeign.getProductById(orderItem.getProductId()));
            }
        }
        return orders;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
        for(OrderItem orderItem : order.getOrderItems()) {
            orderItemRepository.save(orderItem);
            stockMessageSender.reduceProductStock(orderItem);
        }
    }
}
