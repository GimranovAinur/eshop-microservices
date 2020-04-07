package andersen.lab.cartservice.service.impl;

import andersen.lab.cartservice.model.CartItem;
import andersen.lab.cartservice.service.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private static final String CART = "cart";

    @Override
    public void addCartItem(HttpSession session, CartItem item) {
        Map<Long, CartItem> cartItemMap = (Map<Long, CartItem>) session.getAttribute(CART);
        if(cartItemMap == null) {
            cartItemMap = new HashMap<>();
        }
        if(cartItemMap.containsKey(item.getProduct().getId())) {
            CartItem currentCartItem = cartItemMap.get(item.getProduct().getId());
            currentCartItem.setAmount(currentCartItem.getAmount() + item.getAmount());
            cartItemMap.put(item.getProduct().getId(), currentCartItem);
        } else {
            cartItemMap.put(item.getProduct().getId(), item);
        }
        session.setAttribute(CART, cartItemMap);
    }

    @Override
    public void removeCartItem(HttpSession session, Long productId) {
        Map<Long, CartItem> cartItemMap = (HashMap<Long, CartItem>) session.getAttribute(CART);
        if (cartItemMap != null) {
            cartItemMap.remove(productId);
        }
        session.setAttribute(CART, cartItemMap);
    }

    @Override
    public int getCartSize(HttpSession session) {
        return ((Map<Integer, CartItem>) session.getAttribute(CART)).size();
    }

    @Override
    public void cleanCart(HttpSession session) {
        Map<Integer, CartItem> cartItemMap = (HashMap<Integer, CartItem>) session.getAttribute(CART);
        if (cartItemMap != null) {
            cartItemMap.clear();
        }
        session.setAttribute(CART, cartItemMap);
    }
}
