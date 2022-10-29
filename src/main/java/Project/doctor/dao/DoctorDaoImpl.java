package Project.doctor.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import Project.doctor.entity.Doctor;
import Project.doctor.repository.DoctorRepository;
import Project.patient.entity.Patient;
import Project.patient.repository.PatientRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorDaoImpl implements DoctorDao{
    final DoctorRepository doctorRepository;
    final PatientRepository patientRepository;

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Page<Doctor> getAllDoctor(PageRequest pageRequest) {
        return doctorRepository.findAll(pageRequest);
    }

    @Override
    public List<Patient> getPatientInCare(Doctor doctor) {
        return patientRepository.findAllByDoctor(doctor);
    }

    @Override
    public Page<Patient> getPatientInCare(Doctor doctor, PageRequest pageRequest) {
        return patientRepository.findAllByDoctor(doctor, pageRequest);
    }
}
