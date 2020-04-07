package andersen.lab.cartservice.controller;

import andersen.lab.cartservice.dto.ProductDTO;
import andersen.lab.cartservice.model.CartItem;
import andersen.lab.cartservice.service.CartService;
import andersen.lab.cartservice.service.impl.ProductServiceFeign;
import com.netflix.client.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private ProductServiceFeign productServiceFeign;

    private CartService cartService;

    public CartController(ProductServiceFeign productServiceFeign, CartService cartService) {
        this.productServiceFeign = productServiceFeign;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/add/{productId}/{amount}")
    public ResponseEntity addToCart(@PathVariable Long productId,
                                    @PathVariable Integer amount,
                                    HttpSession session) {
        ProductDTO product = productServiceFeign.getProductById(productId);
        CartItem cartItem = CartItem.builder().product(product).amount(amount).build();
        cartService.addCartItem(session, cartItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity deleteFromCart(@PathVariable(value = "id") Long productId,
                               HttpSession session) {
        cartService.removeCartItem(session, productId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAll")
    public ResponseEntity deleteAllFromCart(HttpSession session) {
        cartService.cleanCart(session);
        return new ResponseEntity(HttpStatus.OK);
    }

}
