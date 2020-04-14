package andersen.lab.userservice.controller;

import andersen.lab.userservice.domain.Account;
import andersen.lab.userservice.form.RegistrationForm;
import andersen.lab.userservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAccount(@RequestBody RegistrationForm registrationForm){
        accountService.create(registrationForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

}
