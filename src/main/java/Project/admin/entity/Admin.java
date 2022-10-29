package Project.admin.entity;

import lombok.*;
import Project.security.entity.User;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @OneToOne(mappedBy = "admin",cascade = CascadeType.ALL)
    User user;
}
