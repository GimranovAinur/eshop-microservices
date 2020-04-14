package andersen.lab.userservice.service;

import andersen.lab.userservice.domain.Account;
import andersen.lab.userservice.form.RegistrationForm;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AccountService {

    Account create(@NotNull RegistrationForm signUpRequest);

    Account findAccount(String email);

    List<Account> getAllAccounts();

}
