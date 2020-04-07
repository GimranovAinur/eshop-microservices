package andersen.lab.orderservice.service;

import andersen.lab.orderservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("product-service")
public interface ProductServiceFeign {

    @GetMapping("/api/products/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);

}
