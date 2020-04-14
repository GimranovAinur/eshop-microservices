package andersen.lab.webservice.feign;

import andersen.lab.webservice.form.RegistrationForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface AuthFeignClient {

    @PostMapping("/api/user/registration")
    ResponseEntity registerUser(@RequestBody RegistrationForm user);

    @PostMapping("/api/user/login")
    ResponseEntity login(@RequestBody RegistrationForm user);

}
