package Project.patient.entity;

import lombok.*;
import Project.comment.entity.Comment;
import Project.doctor.entity.Doctor;
import Project.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    User user;

    @Builder.Default
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<VaccineHistory> vaccineHistories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    Doctor doctor;
}
