package andersen.lab.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cart-service")
public interface CartFeignClient {

    @PostMapping(value = "/api/carts/create/{email}")
    void createCart(@PathVariable String email);

}
