package Project.patient.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import Project.doctor.entity.Doctor;
import Project.patient.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByDoctor(Doctor doctor);
    Page<Patient> findAllByDoctor(Doctor doctor, Pageable page);
}
