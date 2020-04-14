package andersen.lab.userservice.service.impl;

import andersen.lab.userservice.domain.Account;
import andersen.lab.userservice.feign.CartFeignClient;
import andersen.lab.userservice.form.RegistrationForm;
import andersen.lab.userservice.repository.AccountRepository;
import andersen.lab.userservice.service.AccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private CartFeignClient cartFeign;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              CartFeignClient cartFeign) {
        this.accountRepository = accountRepository;
        this.cartFeign = cartFeign;
    }

    @Override
    public Account create(@NotNull RegistrationForm registrationForm) {
        Account existing = accountRepository.findByEmail(registrationForm.getEmail());
        Assert.isNull(existing, "Account already exists: "+registrationForm.getEmail());

        Account account = new Account();
        account.setName(registrationForm.getName());
        account.setEmail(registrationForm.getEmail());
        account.setPassword(registrationForm.getPassword());

        cartFeign.createCart(registrationForm.getEmail());
        return account;
    }

    @Override
    public Account findAccount(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}
