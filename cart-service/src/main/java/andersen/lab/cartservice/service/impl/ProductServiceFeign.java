package andersen.lab.cartservice.service.impl;

import andersen.lab.cartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "product-service")
public interface ProductServiceFeign {

    @GetMapping("/api/products/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);

}
