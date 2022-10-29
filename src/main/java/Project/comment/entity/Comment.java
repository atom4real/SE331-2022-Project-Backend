package Project.comment.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import Project.doctor.entity.Doctor;
import Project.patient.entity.Patient;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String content;

    @ManyToOne
    Doctor by;

    @ManyToOne
    Patient to;

    @CreatedDate
    Date createWhen;

    @LastModifiedDate
    Date updateAt;
}
