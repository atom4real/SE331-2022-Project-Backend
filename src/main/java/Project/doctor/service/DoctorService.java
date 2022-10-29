package Project.doctor.service;

import Project.patient.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import Project.comment.entity.Comment;
import Project.comment.entity.CommentRequest;
import Project.doctor.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Patient> getPatientInCare(Long id);
    Page<Patient> getPatientInCare(Long id, PageRequest pageRequest);
    Patient addComment(CommentRequest comment);

    Doctor getDoctor(Long id);
}
