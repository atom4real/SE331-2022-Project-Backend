package Project.doctor.entity;

import lombok.*;
import Project.comment.entity.Comment;
import Project.patient.entity.Patient;
import Project.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String hospital;

    @OneToOne(mappedBy = "doctor",cascade = CascadeType.ALL)
    User user;

    @Builder.Default
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Patient> patients = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "by",cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();
}
