package andersen.lab.authservice.controller;

import andersen.lab.authservice.config.JwtTokenProvider;
import andersen.lab.authservice.domain.Role;
import andersen.lab.authservice.domain.User;
import andersen.lab.authservice.feign.AccountServiceClient;
import andersen.lab.authservice.payload.LoginRequest;
import andersen.lab.authservice.payload.RegistrationRequest;
import andersen.lab.authservice.payload.JwtAuthenticationResponse;
import andersen.lab.authservice.repository.UserRepository;
import andersen.lab.authservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserServiceImpl userService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    private AccountServiceClient accountServiceClient;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserServiceImpl userService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider tokenProvider,
                          AccountServiceClient accountServiceClient) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.accountServiceClient = accountServiceClient;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        if(userRepository.existsUserByEmail(registrationRequest.getEmail())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = new User(registrationRequest.getName(), registrationRequest.getEmail(),
                registrationRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(Role.USER);
        accountServiceClient.createUser(registrationRequest);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}