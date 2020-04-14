package andersen.lab.webservice.controller;

import andersen.lab.webservice.feign.AuthFeignClient;
import andersen.lab.webservice.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController {
    private AuthFeignClient feignClient;

    @Autowired
    public LoginController(AuthFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping
    public String loginForm(@ModelAttribute(value = "user") RegistrationForm registrationForm) {
        return "login";
    }

    @PostMapping
    public String login(RegistrationForm registrationForm) {
        feignClient.login(registrationForm);
        return "login";
    }

}
