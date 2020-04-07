package andersen.lab.orderservice.service.impl;

import andersen.lab.orderservice.domain.OrderItem;
import andersen.lab.orderservice.dto.CartItemDTO;
import andersen.lab.orderservice.service.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    public static final String CART = "cart";

    public List<OrderItem> getOrderItemFromCart(HttpSession session) {
        Map<Integer, CartItemDTO> cartItemMap = (HashMap<Integer, CartItemDTO>) session.getAttribute(CART);
        if (cartItemMap == null)
            cartItemMap = new HashMap<>();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartItemDTO item : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setProductId(item.getProduct().getId());
            orderItem.setAmount(item.getAmount());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public void cleanCart(HttpSession session) {
        Map<Integer, CartItemDTO> cartItemMap = (HashMap<Integer, CartItemDTO>) session.getAttribute(CART);
        if (cartItemMap != null) {
            cartItemMap.clear();
        }
        session.setAttribute(CART, cartItemMap);
    }

}
