package andersen.lab.userservice.controller;

import andersen.lab.userservice.domain.User;
import andersen.lab.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(User user, HttpSession session) {
        if(userService.checkLogin(user)) {
            user = userService.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
            userService.addToSession(session, user);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        userService.removeFromSession(session);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
