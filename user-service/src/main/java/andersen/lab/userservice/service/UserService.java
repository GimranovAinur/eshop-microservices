package andersen.lab.userservice.service;

import andersen.lab.userservice.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface UserService {

    void save(User user);

    boolean checkLogin(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    void addToSession(HttpSession session, User user);

    void removeFromSession(HttpSession session);

}
