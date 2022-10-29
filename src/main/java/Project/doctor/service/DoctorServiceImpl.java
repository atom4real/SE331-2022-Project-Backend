package Project.doctor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import Project.comment.dao.CommentDao;
import Project.comment.entity.Comment;
import Project.comment.entity.CommentRequest;
import Project.doctor.dao.DoctorDao;
import Project.doctor.entity.Doctor;
import Project.patient.dao.PatientDao;
import Project.patient.entity.Patient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    final DoctorDao doctorDao;
    final PatientDao patientDao;
    final CommentDao commentDao;

    @Override
    public List<Patient> getPatientInCare(Long id) {
        Doctor doctor = doctorDao.getDoctor(id);
        return doctorDao.getPatientInCare(doctor);
    }

    @Override
    public Page<Patient> getPatientInCare(Long id, PageRequest pageRequest) {
        Doctor doctor = doctorDao.getDoctor(id);
        return doctorDao.getPatientInCare(doctor, pageRequest);
    }

    @Override
    public Patient addComment(CommentRequest comment) {
        Patient patient = patientDao.getPatient(comment.getPatientId());
        Doctor doctor = doctorDao.getDoctor(comment.getDoctorId());
        Comment com = Comment.builder()
                .content(comment.getContent())
                .to(patient)
                .by(doctor)
                .createWhen(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        commentDao.addComment(com);
        return patient;
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getDoctor(id);
    }
}
