package Project.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import Project.doctor.entity.Doctor;
import Project.patient.entity.Patient;
import Project.patient.entity.VaccineHistoryRequest;
import Project.security.entity.User;
import Project.vaccine.entity.Vaccine;

import java.util.List;

public interface AdminService {
    Patient verifyAsPatient(Long id);
    Doctor verifyAsDoctor(Long id, String hospital);
    Patient addVaccineHistory(Long id, List<VaccineHistoryRequest> history);
    Patient addDoctorToPatient(Long patientID, Long doctorID);
    List<User> getAllUser();
    Page<User> getAllUser(PageRequest pageRequest);
    List<User> getUnEnabledUser();
    Page<User> getUnEnabledUser(PageRequest pageRequest);
    List<Patient> getAllPatient();
    Page<Patient> getAllPatient(PageRequest pageRequest);
    List<Doctor> getAllDoctor();
    Page<Doctor> getAllDoctor(PageRequest pageRequest);
    User getUser(Long id);
    List<Vaccine> getAllVaccine();
}
