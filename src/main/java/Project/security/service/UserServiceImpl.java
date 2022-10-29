package Project.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Project.security.dao.AuthorityDao;
import Project.security.dao.UserDao;
import Project.security.entity.Authority;
import Project.security.entity.AuthorityName;
import Project.security.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    final UserDao userDao;
    final AuthorityDao authorityDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        Authority userRole = Authority.builder().name(AuthorityName.ROLE_USER).build();
        authorityDao.addAuthority(userRole);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .homeTown(user.getHomeTown())
                .imageUrls(user.getImageUrls())
                .enabled(false)
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        newUser.getAuthorities().add(userRole);
        return userDao.addUser(newUser);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
