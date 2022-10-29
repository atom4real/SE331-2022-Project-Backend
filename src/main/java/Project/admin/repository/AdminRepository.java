package Project.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
