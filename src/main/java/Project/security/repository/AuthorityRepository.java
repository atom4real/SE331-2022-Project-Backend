package Project.security.repository;



import org.springframework.data.repository.CrudRepository;
import Project.security.entity.Authority;
import Project.security.entity.AuthorityName;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(AuthorityName input);
}
