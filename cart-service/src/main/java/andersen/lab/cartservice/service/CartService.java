package andersen.lab.cartservice.service;

import andersen.lab.cartservice.model.CartItem;

import javax.servlet.http.HttpSession;

public interface CartService {

    void addCartItem(HttpSession session, CartItem item);

    void removeCartItem(HttpSession session, Long productId);

    int getCartSize(HttpSession session);

    void cleanCart(HttpSession session);

}
