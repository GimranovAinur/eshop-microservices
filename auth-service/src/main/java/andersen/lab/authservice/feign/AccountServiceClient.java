package andersen.lab.authservice.feign;

import andersen.lab.authservice.payload.RegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @PostMapping(value = "/api/accounts/create")
    void createUser(RegistrationRequest registrationRequest);

}
