package Project.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Project.security.entity.Authority;
import Project.security.repository.AuthorityRepository;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
