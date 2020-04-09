package andersen.lab.userservice.service.impl;

import andersen.lab.userservice.domain.User;
import andersen.lab.userservice.repository.UserRepository;
import andersen.lab.userservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER = "user";

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkLogin(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).isPresent();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void addToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }

    public void removeFromSession(HttpSession session) {
        session.removeAttribute(USER);
    }

}
