package andersen.lab.webservice.controller;

import andersen.lab.webservice.feign.AuthFeignClient;
import andersen.lab.webservice.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private AuthFeignClient feignClient;

    @Autowired
    public RegistrationController(AuthFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping
    public String registrationForm(@ModelAttribute(value = "user") RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping
    public String registerUser(RegistrationForm registrationForm) {
        feignClient.registerUser(registrationForm);
        return "redirect:home";
    }

}
