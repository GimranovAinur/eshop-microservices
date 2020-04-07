package andersen.lab.orderservice.service.impl;

import andersen.lab.orderservice.dto.UserDTO;
import andersen.lab.orderservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER = "user";

    @Override
    public UserDTO getUserFromSession(HttpSession session) {
        Object user = session.getAttribute(USER);
        return user == null ? null : (UserDTO) user;
    }
}
