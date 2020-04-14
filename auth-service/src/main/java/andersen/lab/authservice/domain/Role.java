package andersen.lab.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),

    USER("ROLE_USER");

    private String authority;

}
