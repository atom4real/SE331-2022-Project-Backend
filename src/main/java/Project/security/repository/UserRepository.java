package Project.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import Project.security.entity.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByEnabledFalse();
    Page<User> findByEnabledFalse(Pageable pageable);
}
