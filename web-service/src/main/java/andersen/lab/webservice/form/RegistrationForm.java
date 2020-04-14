package andersen.lab.webservice.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationForm {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 6, max = 15)
    private String password;

}
