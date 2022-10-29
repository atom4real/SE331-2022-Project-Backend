package Project.security.service;

import Project.security.controller.JwtAuthenticationRequest;
import Project.security.entity.User;

public interface UserService {
    User findByUsername(String username);
    User addUser(User user);

    User getUser(Long id);
}
