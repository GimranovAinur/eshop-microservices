package andersen.lab.orderservice.controller;

import andersen.lab.orderservice.domain.Order;
import andersen.lab.orderservice.domain.OrderItem;
import andersen.lab.orderservice.dto.UserDTO;
import andersen.lab.orderservice.service.CartService;
import andersen.lab.orderservice.service.OrderService;
import andersen.lab.orderservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    private UserService userService;

    private CartService cartService;

    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public List<Order> list(HttpSession session) {
        UserDTO user = userService.getUserFromSession(session);
        return orderService.getUserOrders(user.getId());
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(HttpSession session) {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(userService.getUserFromSession(session).getId());
        List<OrderItem> orderItems = cartService.getOrderItemFromCart(session);
        Double totalPrice = 0.0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getAmount();
            orderItem.setOrder(order);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderService.saveOrder(order);
        return new ResponseEntity(HttpStatus.OK);
    }

}
