package Project.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.doctor.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
