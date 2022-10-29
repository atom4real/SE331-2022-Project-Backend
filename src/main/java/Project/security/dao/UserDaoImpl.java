package Project.security.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import Project.security.entity.User;
import Project.security.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUser(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public List<User> getUnEnabledUser() {
        return userRepository.findByEnabledFalse();
    }

    @Override
    public Page<User> getUnEnabledUser(PageRequest pageRequest) {
        return userRepository.findByEnabledFalse(pageRequest);
    }
}
