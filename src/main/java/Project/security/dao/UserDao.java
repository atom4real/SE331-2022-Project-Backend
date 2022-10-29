package Project.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import Project.security.entity.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    User getUser(Long id);
    User addUser(User user);
    User updateUser(User user);

    List<User> getAllUser();

    Page<User> getAllUser(PageRequest pageRequest);

    List<User> getUnEnabledUser();
    Page<User> getUnEnabledUser(PageRequest pageRequest);
}
