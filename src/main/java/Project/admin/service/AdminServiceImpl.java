package Project.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import Project.doctor.dao.DoctorDao;
import Project.doctor.entity.Doctor;
import Project.patient.dao.PatientDao;
import Project.patient.dao.VaccineHistoryDao;
import Project.patient.entity.Patient;
import Project.patient.entity.VaccineHistory;
import Project.patient.entity.VaccineHistoryRequest;
import Project.security.dao.AuthorityDao;
import Project.security.dao.UserDao;
import Project.security.entity.Authority;
import Project.security.entity.AuthorityName;
import Project.security.entity.User;
import Project.vaccine.dao.VaccineDao;
import Project.vaccine.entity.Vaccine;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    final UserDao userDao;
    final PatientDao patientDao;
    final DoctorDao doctorDao;
    final AuthorityDao authorityDao;
    final VaccineHistoryDao vaccineHistoryDao;
    final VaccineDao vaccineDao;

    private User getUserDetail(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public Patient verifyAsPatient(Long id) {
        Authority rolePatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        authorityDao.addAuthority(rolePatient);
        User user = getUserDetail(id);
        Patient patient = patientDao.addPatient(new Patient());
        user.setAuthorities(new ArrayList<>());
        user.getAuthorities().add(rolePatient);
        user.setEnabled(true);
        user.setPatient(patient);
        userDao.updateUser(user);
        return patient;
    }

    @Override
    public Doctor verifyAsDoctor(Long id, String hospital) {
        Authority roleDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        authorityDao.addAuthority(roleDoctor);
        User user = getUserDetail(id);
        Doctor doctor = Doctor.builder()
                .hospital(hospital)
                .build();
        doctorDao.addDoctor(doctor);
        user.setAuthorities(new ArrayList<>());
        user.getAuthorities().add(roleDoctor);
        user.setEnabled(true);
        user.setDoctor(doctor);
        userDao.updateUser(user);
        return doctor;
    }

    private Vaccine checkVaccine(String codeName,String fullName) {
        Vaccine vaccine = vaccineDao.getVaccine(codeName);
        if(vaccine == null) {
            vaccine = Vaccine.builder()
                    .codeName(codeName)
                    .fullName(fullName)
                    .build();
            vaccineDao.addVaccine(vaccine);
        }
        return vaccine;
    }

    @Override
    public Patient addVaccineHistory(Long id, List<VaccineHistoryRequest> history) {
        Patient patient = patientDao.getPatient(id);
        for (VaccineHistoryRequest vaccineHistory : history) {
            Vaccine vaccine = checkVaccine(vaccineHistory.getCodeName(),vaccineHistory.getFullName());
            VaccineHistory temp = VaccineHistory.builder()
                    .vaccine(vaccine)
                    .vaccineDate(vaccineHistory.getVaccineDate())
                    .build();
            temp.setPatient(patient);
            vaccineHistoryDao.addVaccineHistory(temp);
        }
        return patient;
    }

    @Override
    public Patient addDoctorToPatient(Long patientId, Long doctorId) {
        Patient patient = patientDao.getPatient(patientId);
        Doctor doctor = doctorDao.getDoctor(doctorId);
        patient.setDoctor(doctor);
        return patientDao.updatePatient(patient);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Page<User> getAllUser(PageRequest pageRequest) {
        return userDao.getAllUser(pageRequest);
    }

    @Override
    public List<User> getUnEnabledUser() {
        return userDao.getUnEnabledUser();
    }

    @Override
    public Page<User> getUnEnabledUser(PageRequest pageRequest) {
        return userDao.getUnEnabledUser(pageRequest);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientDao.getAllPatient();
    }

    @Override
    public Page<Patient> getAllPatient(PageRequest pageRequest) {
        return patientDao.getAllPatient(pageRequest);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorDao.getAllDoctor();
    }

    @Override
    public Page<Doctor> getAllDoctor(PageRequest pageRequest) {
        return doctorDao.getAllDoctor(pageRequest);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<Vaccine> getAllVaccine() {
        return vaccineDao.getAllVaccines();
    }
}
