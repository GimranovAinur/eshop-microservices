package andersen.lab.orderservice.service;

import andersen.lab.orderservice.dto.UserDTO;

import javax.servlet.http.HttpSession;

public interface UserService {

    UserDTO getUserFromSession(HttpSession session);

}
